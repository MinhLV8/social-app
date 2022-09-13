export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('profile'));
    if (user && user.data.token) {
        return {
            headers: {
                Authorization: 'Bearer ' + user.data.token
            }
        };
    } else {
        return {};
    }
}