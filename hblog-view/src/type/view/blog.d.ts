declare global {

    interface BlogDto extends PageDto {
        id?: number;
        categoryId: number | null;
        tagId: number | null;
    }

    interface BlogVo {
        id: number;
        title: string;
        thumbnail: string;
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

    interface ArchiveVo {
        year: number;
        createTime: string;
        blogBriefVos: BlogBriefVo[];
    }

    interface BlogBriefVo {
        id: number;
        title: string;
        thumbnail: string;
        summary: string;
        views: number;
        createTime: string;
        category: CategoryVo;
        tags: TagVo[];
    }

}

export {BlogDto, BlogVo, BlogServeListVo, BlogServeVo, ArchiveVo, BlogBriefVo}
