function setToken(token: string): void {
    localStorage.setItem('token', token);
}

function setUser(user: any): void {
    localStorage.setItem('user', user);
}

function getCurUser(): User {
    const userJson = localStorage.getItem('user') || null
    const user = JSON.parse(userJson as any)
    return user
}

export {setToken, setUser, getCurUser};