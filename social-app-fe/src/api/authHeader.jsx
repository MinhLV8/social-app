export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('jwt'));
    if (user && user.accessToken) {
        return {
            headers: {
                Authorization: 'Bearer ' + user.accessToken
            }
        };
    } else {
        return {};
    }
}