export interface StompClient {
    publish(params: { destination: string, body?: string }): void,
    subscribe(destination: string, callback: (msg: { body: string }) => void): void,
    unsubscribe(destination: string): void,
}

export interface BallAnimation {
    startX: number,
    startY: number,
    endX: number,
    endY: number,
    time: number,
}

export enum PlayerTeam {
    LEFT,
    RIGHT
}

export interface PlayerPosition {
    team: PlayerTeam,
    y: number,
}

export enum PlayerDirection {
    Hold,
    Up,
    Down,
}

export interface PlayerDTO extends PlayerPosition {
    id: string,
    readyToStart: boolean,
}

export type PlayerDTOMap = Record<string, PlayerDTO>

export interface TeamsScore {
    leftTeamScore: number,
    rightTeamScore: number,
}

export enum MessageType {
    ERROR,
    WARNING,
    INFO,
}

export interface Message {
    type: MessageType,
    code: string,
    customMessage: string,
}

export interface GameMessage {
    teamsScore: TeamsScore,
    ballAnimation: BallAnimation,
}

export interface WatchMessage extends Partial<GameMessage> {
    players: PlayerDTO[],
    message?: Message,
}

export interface RegisterMessage extends WatchMessage {
    token?: string,
}
