import React from 'react';
import {PortfolioResponse} from '../api/generated';
import PositionTable from './PositionTable';

interface PortfolioDetailProps {
    portfolio: PortfolioResponse;
    onBack: () => void;
}

const PortfolioDetail: React.FC<PortfolioDetailProps> = ({portfolio, onBack}) => {
    return (
        <section className="portfolio-details">
            <div className="details-header">
                <button className="btn-back" onClick={onBack}>← Back to list</button>
                <h2>Details: {portfolio.name || portfolio.id || 'Unknown'}</h2>
                <p>Client ID: {portfolio.clientId ?? '-'}</p>
            </div>

            <PositionTable positions={portfolio.positions ?? []}/>
        </section>
    );
};

export default PortfolioDetail;
