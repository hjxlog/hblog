import service from "@/utils/request";

export const uploadImage = (file) => {
    const formData = new FormData();
    formData.append('file', file);
    const headers = {
        'Content-Type': 'multipart/form-data',
    };
    return service.post('/admin/file/uploadImage', formData, {headers})
}
