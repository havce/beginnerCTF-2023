import { CSSProperties, useEffect, useMemo } from 'react'
import { enqueueSnackbar } from 'notistack'
import { Typography } from '@mui/joy'
import {
    LEFT_TEAM_X,
    PLAYER_BASE_SVG_PROPS,
    PLAYER_HEIGHT,
    PLAYER_NOT_READY_SVG_PROPS,
    PLAYER_READY_SVG_PROPS,
    PLAYER_TEXT_BASE_STYLE,
    PLAYER_TEXT_LEFT_TEAM_STYLE,
    PLAYER_TEXT_RIGHT_TEAM_STYLE,
    PLAYER_WIDTH,
    RIGHT_TEAM_X,
    START_BUTTON,
} from '../../utils/const'
import { PlayerDTO, PlayerTeam } from '../../utils/interfaces'

export default function PlayFieldPlayer({
    userId,
    playerId,
    player,
}: {
    userId?: string,
    playerId: string,
    player: PlayerDTO,
}) {
    const {
        team,
        y: posY,
        readyToStart
    } = player

    const isUser = useMemo(() => (
        userId === playerId
    ), [userId, playerId])

    const playerStyle: CSSProperties = useMemo(() => ({
        zIndex: isUser ? 10 : undefined,
    }), [isUser])

    const playerSvgProps: React.SVGProps<SVGRectElement> = useMemo(() => {
        const posX = team === PlayerTeam.LEFT
            ? LEFT_TEAM_X
            : RIGHT_TEAM_X
        const extraProps = readyToStart
            ? PLAYER_READY_SVG_PROPS
            : PLAYER_NOT_READY_SVG_PROPS

        return {
            ...PLAYER_BASE_SVG_PROPS,
            ...extraProps,
            x: posX - PLAYER_WIDTH / 2,
            y: posY - PLAYER_HEIGHT / 2,
        }
    }, [team, posY, readyToStart])

    const textSvgProps: React.SVGProps<SVGForeignObjectElement> = useMemo(() => ({
        x: team === PlayerTeam.LEFT
            ? LEFT_TEAM_X - 2 * PLAYER_WIDTH
            : RIGHT_TEAM_X + 2 * PLAYER_WIDTH,
        textAnchor: team === PlayerTeam.LEFT
            ? 'end'
            : 'start',
        overflow: 'visible'
    }), [team])

    const textStyle: React.CSSProperties = useMemo(() => {
        const sideStyle = team === PlayerTeam.LEFT
            ? PLAYER_TEXT_LEFT_TEAM_STYLE
            : PLAYER_TEXT_RIGHT_TEAM_STYLE
        return {
            ...PLAYER_TEXT_BASE_STYLE,
            ...sideStyle,
        }
    }, [team])

    useEffect(() => {
        if (isUser && !readyToStart) {
            enqueueSnackbar('Press ' + START_BUTTON + ' to start', { variant: 'info' })
        }
    }, [isUser, readyToStart])

    return (
        <g>
            <rect
                {...playerSvgProps}
                style={playerStyle}
            />
            <foreignObject
                {...textSvgProps}
                y={posY}
            >
                <Typography
                    noWrap
                    level='body3'
                    variant={isUser ? 'solid' : 'soft'}
                    style={textStyle}
                >
                    {playerId}
                </Typography>
            </foreignObject>
        </g>
    )
}
