import { useState, createContext } from "react";

const BookingExistContext = createContext();

function BookingExistProvider({children}) {
    const [booking, setBooking] = useState();
    const value = {
        booking,
        setBooking
    }
    return (
        <BookingExistContext.Provider value={value}>
            {children}
        </BookingExistContext.Provider>
    )
}

export {BookingExistContext,BookingExistProvider}