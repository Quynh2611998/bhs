import { useState, createContext } from "react";

const BookingContext = createContext();

function BookingProvider({children}) {
    const [date, setDate] = useState('');
    const value = {
        date,
        setDate
    }
    return (
        <BookingContext.Provider value={value}>
            {children}
        </BookingContext.Provider>
    )
}

export {BookingContext,BookingProvider}