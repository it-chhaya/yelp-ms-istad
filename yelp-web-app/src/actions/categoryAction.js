'use server'

async function fetchData() {

    console.log('hello')

    const res = await fetch('http://127.0.0.1:8168/business/api/v1/categories', {
        mode: 'no-cors',
        headers: {
            "Content-Type": "application/json",
        },
    })
        .catch((error) => {
            console.log('error')
            console.log(error)
        })

    return await res.json()
}

export {fetchData}