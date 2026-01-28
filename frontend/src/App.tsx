import {useEffect, useState} from 'react'
import './App.css'
import {portfoliosApi} from './api/client'
import {PortfolioResponse, PortfolioSummaryResponse} from './api/generated'
import PortfolioList from './components/PortfolioList'
import PortfolioDetail from './components/PortfolioDetail'

function App() {
    const [portfolios, setPortfolios] = useState<PortfolioSummaryResponse[]>([]);
    const [selectedPortfolio, setSelectedPortfolio] = useState<PortfolioResponse | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        portfoliosApi.portfoliosGet()
            .then(data => {
                setPortfolios(data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setError('Unable to load portfolios');
                setLoading(false);
            });
    }, []);

    const selectPortfolio = (id: string) => {
        setLoading(true);
        portfoliosApi.portfoliosPortfolioIdGet({portfolioId: id})
            .then(data => {
                setSelectedPortfolio(data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setError('Unable to load portfolio details');
                setLoading(false);
            });
    };

    if (loading && portfolios.length === 0) return <div className="loading">Loading...</div>;
    if (error) return (
        <div className="error-container">
            <p>Error: {error}</p>
            <button className="btn-details" style={{width: 'auto'}} onClick={() => window.location.reload()}>Retry
            </button>
        </div>
    );

  return (
      <div className="app-container">
          <header>
              <h1>H14 Reconciliation Dashboard</h1>
          </header>

          <main>
              {!selectedPortfolio ? (
                  <PortfolioList
                      portfolios={portfolios}
                      onSelectPortfolio={selectPortfolio}
                  />
              ) : (
                  <PortfolioDetail
                      portfolio={selectedPortfolio}
                      onBack={() => setSelectedPortfolio(null)}
                  />
              )}
          </main>

          {loading && selectedPortfolio && (
              <div className="loading-overlay">
                  <div className="loading-spinner">Loading...</div>
              </div>
          )}
      </div>
  )
}

export default App
