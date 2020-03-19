const Request = {
    getStudents() {
        return fetch(
            "http://localhost:8080/student/all", {
                method: 'GET'
            }
        ).then(res => res.json()).then(
            res => {
                console.log('body: ' + JSON.stringify(res))
                return res;
            }
        )
    },
    getJournal() {
        return fetch(
            "http://localhost:8080/journal/all", {
                method: 'GET'
            }
        ).then(res => res.json()).then(
            res => {
                console.log('body: ' + JSON.stringify(res))
                return res;
            }
        )
    },
    getMark(id) {
        return fetch(
            "http://localhost:8080/mark/" + id, {
                method: 'GET'
            }
        ).then(res => res.json()).then(
            res => {
                console.log('body: ' + JSON.stringify(res))
                return res;
            }
        )
    },
};

export default Request;

