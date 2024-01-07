declare global {
    interface BlogDto extends PageDto {
        id?: number;
        title: string;
        thumbnail: string;
        summary: string;
        content: string;
        mdContent: string;
        status: string;
        isRecommend: boolean;
        categoryId: number | null;
        tagIds: number[];
    }

    interface Blog {
        id: number,
        name: string,
        status: string,
        createTime: string,
        updateTime: string
    }
}

export {BlogDto}
