import axios from "axios";
export default async function Local() {

    let data = await axios(`http://127.0.0.1:8168/business/api/v1/categories`)
    const {res} = data;
    console.log(res)

    return (
        <div>
            <h1>Business Page</h1>
            <hr/>
            {/*<h1>{category.name}</h1>*/}
        </div>
    )
}