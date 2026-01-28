import {Configuration, PortfoliosApi} from './generated';

const config = new Configuration({
    basePath: 'http://localhost:8070',
});

export const portfoliosApi = new PortfoliosApi(config);
