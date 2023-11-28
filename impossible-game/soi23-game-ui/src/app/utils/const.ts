import { CSSProperties } from 'react'

// GAME DATA

export const PLAYFIELD_WIDTH = 1000
export const PLAYFIELD_HEIGHT = 750

export const FPS = 60
// ms between frames
export const MS_PER_FRAME = 1000 / FPS

export const BALL_DIAMETER = 20
export const BALL_RADIUS = BALL_DIAMETER / 2
// unit per sec
export const BALL_SPEED = PLAYFIELD_WIDTH / 2

export const PLAYER_WIDTH = 10
export const PLAYER_HEIGHT = 100
export const PLAYER_RADIUS = Math.min(PLAYER_WIDTH, PLAYER_HEIGHT) / 2
// unit per sec
export const PLAYER_SPEED = PLAYFIELD_HEIGHT / 3
// unit per frame
export const PLAYER_STEP = Math.floor(PLAYER_SPEED / FPS)

export const LEFT_TEAM_X = Math.floor(PLAYFIELD_WIDTH / 20)
export const RIGHT_TEAM_X = Math.floor(PLAYFIELD_WIDTH - LEFT_TEAM_X)

// PLAYFIELD STYLE

export const PLAYFIELD_SVG_VIEWBOX = `0 0 ${PLAYFIELD_WIDTH} ${PLAYFIELD_HEIGHT}`
export const PLAYFIELD_SVG_WIDTH = '100vmin'
export const PLAYFIELD_SVG_HEIGHT = '75vmin'

export const PLAYFIELD_STYLE: CSSProperties = Object.freeze({
    position: 'relative',
    backgroundColor: '#E0E0E0'
})

export const PLAYFIELD_TEXT_STYLE: CSSProperties = Object.freeze({
    position: 'absolute',
    transform: 'translate(-50%, -50%)',
    top: '50%',
    left: '50%',
})

// BALL STYLE

export const BALL_BASE_SVG_PROPS: React.SVGProps<SVGCircleElement> = Object.freeze({
    fill: '#FF2C07',
    r: BALL_RADIUS,
})

// PLAYER STYLE

export const PLAYER_BASE_SVG_PROPS: React.SVGProps<SVGRectElement> = Object.freeze({
    rx: PLAYER_RADIUS,
    width: PLAYER_WIDTH,
    height: PLAYER_HEIGHT,
})
export const PLAYER_READY_SVG_PROPS: React.SVGProps<SVGRectElement> = Object.freeze({
    fill: '#7C4DFF',
})
export const PLAYER_NOT_READY_SVG_PROPS: React.SVGProps<SVGRectElement> = Object.freeze({
    fill: '#F7D302',
})

export const PLAYER_TEXT_BASE_STYLE: CSSProperties = Object.freeze({
    width: 'fit-content',
})
export const PLAYER_TEXT_LEFT_TEAM_STYLE: CSSProperties = Object.freeze({
    transform: 'translate(-100%, -50%)',
})
export const PLAYER_TEXT_RIGHT_TEAM_STYLE: CSSProperties = Object.freeze({
    transform: 'translate(100%, -50%)',
})

// Text constants
export const START_BUTTON = 'Space'
export const MOVE_UP_BUTTON = 'KeyW'
export const MOVE_DOWN_BUTTON = 'KeyS'
