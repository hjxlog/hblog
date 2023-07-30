declare global {
    interface CategoryDto extends Page {
        id?: number
        name: string
        status: string
    }

    interface Category {
        id: number,
        name: string,
        status: string,
        createTime: string,
        updateTime: string
    }
}

export {CategoryDto}