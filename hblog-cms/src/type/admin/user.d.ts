declare global {

    interface User {
        id?: number,
        username?: string,
        nickname?: string,
        password?: string
    }

    interface UserInfoDto {
        id: number,
        nickname: string
    }

    interface UserUpdatePasswordDto {
        id?: number,
        oldPassword?: string,
        newPassword?: string,
        confirmPassword?: string
    }

}

export {User}