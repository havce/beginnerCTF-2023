import {
    PLAYFIELD_HEIGHT,
    PLAYER_HEIGHT,
    PLAYER_STEP,
} from './const'
import { PlayerDirection, PlayerPosition } from './interfaces'

export default class Player {
    private direction: PlayerDirection

    constructor(
        private position: PlayerPosition,
        private onChangePositionY?: (playerPositionY: number) => void
    ) {
        this.direction = PlayerDirection.Hold
    }

    public getPosition() {
        return this.position
    }

    public setPosition(position: PlayerPosition) {
        this.position = position
    }

    public getDirection() {
        return this.direction
    }

    public setDirection(direction: PlayerDirection) {
        this.direction = direction
    }

    public setOnChangePositionY(onChangePositionY: (playerPositionY: number) => void) {
        this.onChangePositionY = onChangePositionY
    }

    public animate() {
        const y = this.position.y

        let newY = y

        switch (this.direction) {
            case PlayerDirection.Up:
                newY = Math.max(PLAYER_HEIGHT / 2, y - PLAYER_STEP)
                break
            case PlayerDirection.Down:
                newY = Math.min(PLAYFIELD_HEIGHT - PLAYER_HEIGHT / 2, y + PLAYER_STEP)
                break
            default:
                return
        }

        if (newY !== this.position.y) {
            this.position.y = newY
            if (this.onChangePositionY) {
                this.onChangePositionY(this.position.y)
            }
        }
    }
}
