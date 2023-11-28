const pramzanDecoder = (pramzanQuery) => {

    const translations = {
        "chapa": "SELECT",
        "da": "FROM",
        "in dua": "WHERE",
        "cognomm": "surname",
        "nomm": "name"
    }

    //Remove don't allowed english words --> name and surname are allowed
    const noEnglishQuerty = pramzanQuery.replace(/select|from|where/gi, () => {
        throw new Error("L'inglese me al capisa mia");
    });

    //Translate the query
    return noEnglishQuerty.replace(/chapa|da|in dua|cognomm|nomm/gi, (matched) => {
        return translations[matched];
    })

}

module.exports = {pramzanDecoder};