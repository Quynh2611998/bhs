import { useState, createContext } from "react";

const BookingOptionContext = createContext();


function BookingOptionProvider({children}) {
    const [bookingOption, setBookingOption] = useState();
    const value = {
        bookingOption,
        setBookingOption
    }
    return (
        <BookingOptionContext.Provider value={value}>
            {children}
        </BookingOptionContext.Provider>
    )
}

export {BookingOptionContext,BookingOptionProvider}