declare global {

    interface CommentDto {
        id?: number;
        categoryId: number | null;
        tagId: number | null;
    }

}

export {CommentDto}
