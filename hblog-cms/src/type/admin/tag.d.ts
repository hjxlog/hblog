declare global {
    interface TagDto extends PageDto {
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
