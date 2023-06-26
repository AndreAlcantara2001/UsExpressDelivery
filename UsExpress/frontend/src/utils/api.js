const domain = "http://localhost:7070/";//
async function get(path){
    const resp = await fetch(
        domain+path,{
            method: 'GET'
        }
    );
    return resp;
}
async function save(path,body){
    const resp = await fetch(
        domain+path,{
            method: 'POST',
            headers:{
                "content-type": "application/json"
            },
            body:JSON.stringify(body)

        }
    );
    return resp;
}

export default{
    get,
    save
}


