declare global {
    interface TagDto extends Page {
        id?: number
        name: string
    }

    interface Tag {
        id: number,
        name: string,
        createTime: string,
        updateTime: string
    }
}

export {TagDto}