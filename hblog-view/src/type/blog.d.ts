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

    interface BlogServeListVo {
        id: number;
        title: string;
        createTime: string;
    }

    interface BlogServeVo {
        id: number;
        title: string;
        summary: string;
        content: string;
        views: string;
        createTime: string;
        category: CategoryVo;
        tags: TagVo[];
    }

}

export {BlogDto, BlogVo}