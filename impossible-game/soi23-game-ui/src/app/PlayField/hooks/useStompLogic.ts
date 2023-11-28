import { useCallback, useMemo } from 'react'
import useStompClient from '../../stomp/StompClient/hooks/useStompClient'
import {
    BallAnimation,
    GameMessage,
    Message,
    PlayerDTO,
    RegisterMessage,
    TeamsScore,
} from '../../utils/interfaces'

const APP_PREFIX = '/app/game.'
const TOPIC_PREFIX = '/topic/game.'

const getDestination = ({
    gameId,
    prefix,
    suffix,
}: {
    gameId: string,
    prefix: string,
    suffix?: string,
}) => (
    gameId !== ''
        ? `${prefix}${gameId}${suffix || ''}`
        : undefined
)

export default function useStompLogic({
    gameId,
    playerId,
    playerToken,
    onTokenChange,
    onTeamsScoreChange,
    onBallAnimationChange,
    onPlayerDTOChange,
    onMessageChange,
}: {
    gameId: string,
    playerId: string,
    playerToken: string,
    onTokenChange(token: string): void,
    onTeamsScoreChange(teamsScore: TeamsScore): void,
    onBallAnimationChange(ballAnimation: BallAnimation): void,
    onPlayerDTOChange(player: PlayerDTO): void,
    onMessageChange(message: Message): void,
}) {
    // REGISTER

    const registerDestination = useMemo(() => (
        playerId && getDestination({ gameId, prefix: APP_PREFIX, suffix: `.player.${playerId}` })
    ), [gameId, playerId])

    const handleRegisterMessage = useCallback(({ body }: { body: string }) => {
        const registerMessage: RegisterMessage = JSON.parse(body)
        if (registerMessage.teamsScore) {
            onTeamsScoreChange(registerMessage.teamsScore)
        }
        if (registerMessage.ballAnimation) {
            onBallAnimationChange(registerMessage.ballAnimation)
        }
        if (registerMessage.token) {
            onTokenChange(registerMessage.token)
        }
        if (registerMessage.message) {
            onMessageChange(registerMessage.message)
        }
        registerMessage.players.forEach(onPlayerDTOChange)
    }, [onTokenChange, onTeamsScoreChange, onBallAnimationChange, onMessageChange, onPlayerDTOChange])

    useStompClient({
        destination: registerDestination,
        onMessage: handleRegisterMessage
    })

    // MAIN TOPIC

    const mainTopicDestination = useMemo(() => (
        getDestination({ gameId, prefix: TOPIC_PREFIX })
    ), [gameId])

    const handleMainTopicMessage = useCallback(({ body }: { body: string }) => {
        const gameMessage: GameMessage = JSON.parse(body)
        onTeamsScoreChange(gameMessage.teamsScore)
        onBallAnimationChange(gameMessage.ballAnimation)
    }, [onTeamsScoreChange, onBallAnimationChange])

    useStompClient({
        destination: mainTopicDestination,
        onMessage: handleMainTopicMessage
    })

    // BALL TOPIC

    const ballTopicDestination = useMemo(() => (
        getDestination({ gameId, prefix: TOPIC_PREFIX, suffix: '.ball' })
    ), [gameId])

    const handleBallTopicMessage = useCallback(({ body }: { body: string }) => {
        const ballAnimation: BallAnimation = JSON.parse(body)
        onBallAnimationChange(ballAnimation)
    }, [onBallAnimationChange])

    useStompClient({
        destination: ballTopicDestination,
        onMessage: handleBallTopicMessage
    })

    // PLAYERS TOPIC

    const playersTopicDestination = useMemo(() => (
        getDestination({ gameId, prefix: TOPIC_PREFIX, suffix: '.players' })
    ), [gameId])

    const handlePlayersTopicMessage = useCallback(({ body }: { body: string }) => {
        const playerDTO: PlayerDTO = JSON.parse(body)
        onPlayerDTOChange(playerDTO)
    }, [onPlayerDTOChange])

    useStompClient({
        destination: playersTopicDestination,
        onMessage: handlePlayersTopicMessage
    })

    // MESSAGE TOPIC

    const messageTopicDestination = useMemo(() => (
        playerToken && getDestination({ gameId, prefix: TOPIC_PREFIX, suffix: `.messages.${playerToken}` })
    ), [gameId, playerToken])

    /*
    Handle Message topic messages
    */
    const handleMessageTopicMessage = useCallback(({ body }: { body: string }) => {
        const Message: Message = JSON.parse(body)
        onMessageChange(Message)
    }, [onMessageChange])

    useStompClient({
        destination: messageTopicDestination,
        onMessage: handleMessageTopicMessage
    })

    // SEND START

    const startDestination = useMemo(() => (
        getDestination({ gameId, prefix: APP_PREFIX, suffix: '.start' })
    ), [gameId])

    const {
        send: sendStart
    } = useStompClient({ destination: startDestination })

    // SEND ANIMATION ENDED

    const animationEndedDestination = useMemo(() => (
        getDestination({ gameId, prefix: APP_PREFIX, suffix: '.animation' })
    ), [gameId])

    const {
        send: sendAnimationEnded
    } = useStompClient({ destination: animationEndedDestination })

    // SEND POSITION

    const positionDestination = useMemo(() => (
        getDestination({ gameId, prefix: APP_PREFIX, suffix: '.position' })
    ), [gameId])

    const {
        send: sendPosition
    } = useStompClient({ destination: positionDestination })

    return {
        sendStart,
        sendAnimationEnded,
        sendPosition
    }
}
