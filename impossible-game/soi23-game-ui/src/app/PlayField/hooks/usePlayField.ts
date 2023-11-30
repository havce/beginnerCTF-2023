import {
    ChangeEvent,
    KeyboardEvent,
    useCallback,
    useEffect,
    useMemo,
    useRef,
    useState
} from 'react'
import {
    BallAnimation,
    PlayerDTO,
    PlayerDTOMap,
    PlayerDirection,
    TeamsScore,
} from '../../utils/interfaces'
import {
    BALL_BASE_SVG_PROPS,
    START_BUTTON,
    MOVE_UP_BUTTON,
    MOVE_DOWN_BUTTON,
} from '../../utils/const'
import Arena from '../../utils/Arena'
import useSubmit from './useSubmit'
import useStompLogic from './useStompLogic'
import useMessageHandler from './useMessageHandler'

interface BallProps extends React.SVGProps<SVGCircleElement> {
    style: React.CSSProperties,
}

function generateGuid() {
    let result = '';
    for (let j = 0; j < 32; j++) {
        if (j == 8 || j == 12 || j == 16 || j == 20)
            result = result + '-';
        const i = Math.floor(Math.random() * 16).toString(16).toUpperCase();
        result = result + i;
    }
    return result;
}

export default function usePlayField() {
    const [pendingGameId, setPendingGameId] = useState<string>(generateGuid())
    const [pendingPlayerId, setPendingPlayerId] = useState<string>('')
    const [token, setToken] = useState<string>('')
    const [teamsScore, setTeamsScore] = useState<TeamsScore | null>(null)
    const [ballAnimation, setBallAnimation] = useState<BallAnimation | null>(null)
    const [playerDTOMap, setPlayerDTOMap] = useState<PlayerDTOMap>({})

    const arenaRef = useRef<Arena>(new Arena())

    const ballProps: BallProps = useMemo(() => {
        const customStyle: React.CSSProperties = {}
        if (ballAnimation !== null) {
            document.documentElement.style.setProperty('--ball-end-y', `${ballAnimation.endY}px`)
            document.documentElement.style.setProperty('--ball-end-x', `${ballAnimation.endX}px`)
            customStyle.animationName = 'ballAnimation'
            customStyle.animationTimingFunction = 'linear'
            customStyle.animationFillMode = 'forwards'
            customStyle.animationDuration = `${ballAnimation.time}s`
        } else {
            customStyle.visibility = 'hidden'
        }
        return {
            style: customStyle,
            ...BALL_BASE_SVG_PROPS,
            cx: ballAnimation?.startX,
            cy: ballAnimation?.startY,
        }
    }, [ballAnimation])

    const {
        gameId,
        playerId,
        handleButtonClick
    } = useSubmit({
        pendingGameId,
        pendingPlayerId,
        disableEdit: !!token,
    })

    const handleBallAnimationChange = useCallback((ballAnim: BallAnimation) => {
        setBallAnimation((oldBallAnim) => {
            if (oldBallAnim?.endX === ballAnim.endX && oldBallAnim?.endY === ballAnim.endY) {
                return oldBallAnim
            }
            window.requestAnimationFrame(
                () => setBallAnimation(ballAnim)
            )
            return null
        })
    }, [])

    const handlePlayerDTOChange = useCallback((playerDTO: PlayerDTO) => {
        if (playerId === playerDTO.id) {
            arenaRef.current.setPlayerPosition({ team: playerDTO.team, y: playerDTO.y })
        }
        /*
        Set the new value of playerDTOMap
        */
        setPlayerDTOMap((oldVal) => ({
            ...oldVal,
            [playerDTO.id]: playerDTO
        }))
    }, [playerId])

    const {
        handleMessageChange
    } = useMessageHandler()

    const {
        sendStart,
        sendAnimationEnded,
        sendPosition
    } = useStompLogic({
        gameId,
        playerId,
        playerToken: token,
        onTokenChange: setToken,
        onTeamsScoreChange: setTeamsScore,
        onBallAnimationChange: handleBallAnimationChange,
        onPlayerDTOChange: handlePlayerDTOChange,
        onMessageChange: handleMessageChange,
    })

    const handleKeyDown = useCallback(({ code }: KeyboardEvent) => {
        /*
        Map the key so that you can:
            - set the moving direction of the player
            - request the start of the game
        */
        const playerRef = arenaRef.current.getPlayer()

        switch (code) {
            case START_BUTTON:
                sendStart(JSON.stringify({ 'playerId': playerId, 'token': token }))
                break
            case MOVE_UP_BUTTON:
                playerRef?.setDirection(PlayerDirection.Up)
                break
            case MOVE_DOWN_BUTTON:
                playerRef?.setDirection(PlayerDirection.Down)
                break
            default:
                break
        }
    }, [playerId, sendStart, token])

    const handleKeyUp = useCallback(({ code }: KeyboardEvent) => {
        /*
        Map the key so that you can reset the moving direction
        of the player.
        Be aware that the user could have already pressed the key
        corresponding to the opposite player direction
        */
        const playerRef = arenaRef.current.getPlayer()

        switch (code) {
            case MOVE_UP_BUTTON:
                if (playerRef?.getDirection() === PlayerDirection.Up) {
                    playerRef?.setDirection(PlayerDirection.Hold)
                }
                break
            case MOVE_DOWN_BUTTON:
                if (playerRef?.getDirection() === PlayerDirection.Down) {
                    playerRef?.setDirection(PlayerDirection.Hold)
                }
                break
            default:
                break
        }
    }, [])

    const handleAnimationEnd = useCallback(() => {
        /*
        Notify the backend that the animation ended
        */
        sendAnimationEnded()
    }, [sendAnimationEnded])

    const handlePlayerPositionYChange = useCallback((playerPositionY: number) => {
        /*
        Notify the backend the new player position
        */
        sendPosition(JSON.stringify({ 'playerId': playerId, 'token': token, 'y': playerPositionY }))
    }, [playerId, sendPosition, token])

    const handlePlayerIdChange = useCallback((event: ChangeEvent<HTMLInputElement>) => {
        setPendingPlayerId(event.target.value)
    }, [])

    useEffect(() => {
        arenaRef.current.getPlayer()?.setOnChangePositionY(handlePlayerPositionYChange)
    }, [handlePlayerPositionYChange, teamsScore])

    return {
        playerId: pendingPlayerId,
        disableEdit: !!token,
        teamsScore,
        playerDTOMap,
        ballProps,
        handleKeyDown,
        handleKeyUp,
        handleAnimationEnd,
        handlePlayerIdChange,
        handleButtonClick,
    }
}
