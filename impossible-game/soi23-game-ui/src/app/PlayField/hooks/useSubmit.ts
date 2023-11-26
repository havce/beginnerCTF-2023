import { useCallback, useState } from 'react'
import { enqueueSnackbar } from 'notistack'

export default function useSubmit({
    pendingGameId,
    pendingPlayerId,
    disableEdit,
}: {
    pendingGameId: string,
    pendingPlayerId: string,
    disableEdit: boolean,
}) {
    const [gameId, setGameId] = useState<string>('')
    const [playerId, setPlayerId] = useState<string>('')

    const handleButtonClick = useCallback(() => {
        if (!disableEdit) {
            setGameId(pendingGameId)
            if (pendingPlayerId) {
                setPlayerId(pendingPlayerId)
            } else {
                enqueueSnackbar('Insert player ID to start the game', { variant: 'warning' })
            }
        }
    }, [pendingGameId, pendingPlayerId, disableEdit])

    return {
        gameId,
        playerId,
        handleButtonClick
    }
}
