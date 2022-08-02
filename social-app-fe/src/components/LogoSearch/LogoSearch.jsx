import { UilSearch } from '@iconscout/react-unicons'
import React from 'react'
import './LogoSearch.css'
const LogoSearch = () => {
    return (
        <div className="LogoSearch">
            <img src="../../assets/icons/care-2387662-1991058.png" alt="" />
            <div className="Search">
                <input type="text" placeholder='Tìm kiếm ở đây...' />
                <div className="s-icon">
                    <UilSearch />
                </div>
            </div>
        </div>
    )
}

export default LogoSearch