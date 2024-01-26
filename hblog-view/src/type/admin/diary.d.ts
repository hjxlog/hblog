declare global {
    interface Diary {
        id: number,
        content: string,
        mdContent: string,
        status: string,
        createTime: string,
        updateTime: string
    }

    interface DiarySaveDto {
        id: number,
        content: string,
        mdContent: string,
        status: string
    }
}

export {Diary, DiarySaveDto}
