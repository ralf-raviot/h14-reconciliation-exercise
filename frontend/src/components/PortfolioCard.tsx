import React from 'react';
import {PortfolioSummaryResponse} from '../api/generated';

interface PortfolioCardProps {
    portfolio: PortfolioSummaryResponse;
    onClick: (id: string) => void;
}

const PortfolioCard: React.FC<PortfolioCardProps> = ({portfolio, onClick}) => {
    return (
        <div className="card portfolio-card" onClick={() => portfolio.id && onClick(portfolio.id)}>
            <h3>{portfolio.name || 'Unnamed Portfolio'}</h3>
            <p><strong>ID:</strong> {portfolio.id ?? '-'}</p>
            <p><strong>Client:</strong> {portfolio.clientId ?? '-'}</p>
            <button className="btn-details">View Details</button>
        </div>
    );
};

export default PortfolioCard;
