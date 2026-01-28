import React from 'react';
import {BankResponse, ConsolidatedPositionResponse} from '../api/generated';
import StatusBadge from './StatusBadge';

interface PositionTableProps {
    positions: ConsolidatedPositionResponse[];
}

const PositionTable: React.FC<PositionTableProps> = ({positions}) => {
    const formatBankName = (bank?: BankResponse | null) => {
        if (!bank) return 'Unknown Bank';
        switch (bank) {
            case 'BANCAPOSTALE':
                return 'Banca Postale';
            case 'NEOBANCA':
                return 'Neo Banca';
            case 'SUPERBANCA':
                return 'Super Banca';
            default:
                return bank;
        }
    };

    return (
        <div className="table-container">
            <table>
                <thead>
                <tr>
                    <th>Security</th>
                    <th>ISIN</th>
                    <th>Status</th>
                    <th>Internal Qty</th>
                    <th>Bank(s) Qty</th>
                    <th>Market Price</th>
                </tr>
                </thead>
                <tbody>
                {positions.map((pos, idx) => {
                    const totalBankQty = pos.bankPositions?.reduce((acc, b) => acc + (b.quantity ?? 0), 0);

                    return (
                        <tr key={idx}>
                            <td>{pos.security?.name ?? 'Unknown'}</td>
                            <td><code>{pos.security?.isin ?? '-'}</code></td>
                            <td>
                                <StatusBadge status={pos.status}/>
                            </td>
                            <td>{pos.internalPosition?.quantity ?? '-'}</td>
                            <td>
                                <div className="bank-qty-cell">
                                    {totalBankQty !== undefined && totalBankQty !== 0 ? (
                                        <div className="tooltip">
                                            {totalBankQty}
                                            {pos.bankPositions && pos.bankPositions.length > 0 && (
                                                <div className="tooltip-content">
                                                    <div className="tooltip-header">Bank Breakdown</div>
                                                    {pos.bankPositions.map((bp, i) => (
                                                        <div key={i} className="tooltip-row">
                                                            <span>{formatBankName(bp.bank)}</span>
                                                            <span className="qty">{bp.quantity}</span>
                                                        </div>
                                                    ))}
                                                </div>
                                            )}
                                        </div>
                                    ) : (
                                        '-'
                                    )}
                                </div>
                            </td>
                            <td>{pos.security?.marketPrice} {pos.security?.currency}</td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
};

export default PositionTable;
