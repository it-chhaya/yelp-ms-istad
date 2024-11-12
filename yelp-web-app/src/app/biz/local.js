'use client'

import {useEffect, useState} from "react";

export default function Local(data) {

    const [category, setCategory] = useState({})

    useEffect(() => {
        const onLoad = async () => {
            return await data()
        };
        onLoad().then(json => setCategory(json));
    }, [category, data])

    return (
        <div>
            <h1>Category Page</h1>
            <hr/>
            <h1>{category.name}</h1>
        </div>
    )
}