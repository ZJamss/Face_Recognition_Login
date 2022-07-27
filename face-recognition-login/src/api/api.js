import axios from "@/utils/http";
import unity from "@/utils/unity";

export default {
    login(file) {
        let form = new FormData();
        form.append("stuPhoto",file)
        return unity(axios.post("/login", form))
    },

    register(data) {
        let form = new FormData();
        form.append("stuName",data.stuName)
        form.append("stuPhoto",data.stuPhoto)
        return unity(axios.post("/register", form,{
            headers :{
                'Content-Type': 'multipart/form-data'
            }
        }))
    }
}