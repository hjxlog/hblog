declare global {

    interface BlogDto extends Page {
        id?: number;
        categoryId: number | null;
        tagId: number | null;
    }

    interface BlogVo {
        id: number;
        title: string;
        summary: string;
        content: string;
        views: string;
        createTime: string;
        category: string;
        tags: string;
    }

}

export {BlogDto, BlogVo}