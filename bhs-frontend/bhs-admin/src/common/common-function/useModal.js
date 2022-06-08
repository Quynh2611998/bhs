import { useState } from "react";

const useModal = () => {

    const [isShowing,setIsShowing] = useState(false);
    const [isShowing2,setIsShowing2] = useState(false); 
    const [isShowing3,setIsShowing3] = useState(false);
    const [isShowing4,setIsShowing4] = useState(false);  


    function toggle() {
        setIsShowing(!isShowing);
       
    }

    function toggle2() {
       
        setIsShowing2(!isShowing2);
    }
    function toggle3() {
       
        setIsShowing3(!isShowing3);
    }
    function toggle4() {
       
        setIsShowing4(!isShowing4);
    }
    return {
        isShowing,
        isShowing2,
        isShowing3,
        isShowing4,
        toggle,
        toggle2,
        toggle3,
        toggle4
    }
}
export default useModal;

