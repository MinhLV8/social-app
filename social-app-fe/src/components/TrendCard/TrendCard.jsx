import React from 'react'
import './TrendCard.css'

import {TrendData} from '../../Data/TrendData.js'
import {nFormatter} from '../../utils/Utils'

const TrendCard = () => {
    return (
        <div className="TrendCard">
            <h3>Xu hướng:</h3>
            {TrendData.map((trend) => {
                return (
                    <div className="trend">
                        <span>#{trend.name}</span>
                        <span>{nFormatter(trend.shares)} chia sẻ</span>
                    </div>
                )
            })}

        </div>
    )
}

export default TrendCard