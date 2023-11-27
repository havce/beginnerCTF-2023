import Player from './Player'
import { MS_PER_FRAME } from './const'
import { PlayerPosition } from './interfaces'

export default class Arena {
    private msPrevFrame: number
    private player?: Player

    constructor() {
        this.msPrevFrame = window.performance.now()
    }

    public getPlayer() {
        return this.player
    }

    public setPlayerPosition(playerPosition: PlayerPosition) {
        /*
        If the player is already present, set its new position.
        Otherwise instantiate it and call animate
        */
        if (this.player) {
            this.player.setPosition(playerPosition)
        } else {
            this.player = new Player(playerPosition, undefined)
            this.animate()
        }
    }

    public animate() {
        if (this.player) {
            window.requestAnimationFrame(this.animate.bind(this))
            const msNow = window.performance.now()
            const msPrev = this.msPrevFrame
            const msDelta = msNow - msPrev

            if (msDelta >= MS_PER_FRAME) {
                // Valid frame
                this.player?.animate()
                const excessTime = msDelta % MS_PER_FRAME
                this.msPrevFrame = msNow - excessTime
            }
        }
    }
}
