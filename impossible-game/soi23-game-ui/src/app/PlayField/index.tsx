import { Fragment } from 'react'
import {
    Button,
    Input,
    Stack,
    Typography,
} from '@mui/joy'
import {
    PLAYFIELD_STYLE,
    PLAYFIELD_SVG_VIEWBOX,
    PLAYFIELD_SVG_WIDTH,
    PLAYFIELD_SVG_HEIGHT,
    PLAYFIELD_TEXT_STYLE,
} from '../utils/const'
import usePlayField from './hooks/usePlayField'
import PlayFieldPlayer from './PlayFieldPlayer'

export default function PlayField() {

    const {
        playerId,
        disableEdit,
        teamsScore,
        playerDTOMap,
        ballProps,
        handleKeyDown,
        handleKeyUp,
        handleAnimationEnd,
        handlePlayerIdChange,
        handleButtonClick,
    } = usePlayField()

    return (
        <Fragment>
            <div style={PLAYFIELD_STYLE}>
                {teamsScore && (
                    <Typography level='h1' style={PLAYFIELD_TEXT_STYLE}>
                        {`${teamsScore.leftTeamScore}-${teamsScore.rightTeamScore}`}
                    </Typography>
                )}
                <svg
                    tabIndex={0}
                    overflow='visible'
                    viewBox={PLAYFIELD_SVG_VIEWBOX}
                    width={PLAYFIELD_SVG_WIDTH}
                    height={PLAYFIELD_SVG_HEIGHT}
                    onKeyDown={handleKeyDown}
                    onKeyUp={handleKeyUp}
                >
                    <circle
                        {...ballProps}
                        onAnimationEnd={handleAnimationEnd}
                    />
                    {Object.keys(playerDTOMap).map((playerDTOId) => (
                        <PlayFieldPlayer
                            key={playerDTOId}
                            userId={playerId}
                            playerId={playerDTOId}
                            player={playerDTOMap[playerDTOId]}
                        />
                    ))}
                </svg>
            </div>
            <Stack
                direction='row'
                spacing={2}
            >
                <Input
                    size='sm'
                    variant='soft'
                    placeholder="Player ID"
                    value={playerId}
                    disabled={disableEdit}
                    onChange={handlePlayerIdChange}
                />
                <Button
                    size='sm'
                    disabled={disableEdit}
                    onClick={handleButtonClick}
                >
                    connect
                </Button>
            </Stack>
        </Fragment>
    )
}
