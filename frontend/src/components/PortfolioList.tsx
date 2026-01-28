import React from 'react';
import {PortfolioSummaryResponse} from '../api/generated';
import PortfolioCard from './PortfolioCard';

interface PortfolioListProps {
    portfolios: PortfolioSummaryResponse[];
    onSelectPortfolio: (id: string) => void;
}

const PortfolioList: React.FC<PortfolioListProps> = ({portfolios, onSelectPortfolio}) => {
    return (
        <section className="portfolio-list">
            <h2>Portfolios List</h2>
            <div className="grid">
                {portfolios.map(p => (
                    <PortfolioCard
                        key={p.id}
                        portfolio={p}
                        onClick={onSelectPortfolio}
                    />
                ))}
            </div>
        </section>
    );
};

export default PortfolioList;
