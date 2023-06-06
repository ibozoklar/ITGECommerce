
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import {BasketPage} from './BasketPage';

export const Routes = () => {
    return (
        <Router>
            <Switch>
                <Route path='/'>
                    <BasketPage />
                </Route>
            </Switch>
        </Router>

    )

}