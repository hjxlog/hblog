import {reactive, ref} from 'vue'

const isMobile = ref(window.innerWidth <= 768);

export const menuItem = reactive({
    isCollapse: isMobile.value,
    isMobile: isMobile.value
})
