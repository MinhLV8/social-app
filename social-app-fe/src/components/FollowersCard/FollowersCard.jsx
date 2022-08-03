import React from 'react'
import './FollowersCard.css'

import {Followers} from '../../Data/FollowersData'

const FollowersCard = () => {
    return (
        <div className="FollowersCard">
            <h3>Những người theo dõi bạn</h3>
            {Followers.map((follower, id) => {
                return (
                    <div className="follower">
                        <div>
                            <img src={follower.img} alt="" className='followerImage'/>
                            <div className="name">
                                <span>{follower.name}</span>
                                <span>@{follower.username}</span>
                            </div>
                        </div>
                        <button className='button fc-button'>
                            Theo dõi
                        </button>
                    </div>
                )
            })}
        </div>
    )
}

export default FollowersCard