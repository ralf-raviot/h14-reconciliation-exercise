import React from 'react';
import {ConsolidationStatusResponse} from '../api/generated';

interface StatusBadgeProps {
    status?: ConsolidationStatusResponse;
}

const StatusBadge: React.FC<StatusBadgeProps> = ({status}) => {
    if (!status) return null;

    const getStatusLabel = (status: ConsolidationStatusResponse) => {
        switch (status) {
            case ConsolidationStatusResponse.Consolidated:
                return 'Consolidated';
            case ConsolidationStatusResponse.NotConsolidated:
                return 'Not Consolidated';
            case ConsolidationStatusResponse.UnknownPosition:
                return 'Unknown Position';
            case ConsolidationStatusResponse.MissingBankPosition:
                return 'Missing Bank Position';
            default:
                return status;
        }
    };

    return (
        <span className={`status-badge ${status.toLowerCase().replace(/_/g, '-')}`}>
      {getStatusLabel(status)}
    </span>
    );
};

export default StatusBadge;
