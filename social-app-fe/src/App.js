import "./App.css";
//import Auth from "./pages/Auth/Auth";
import data from '@emoji-mart/data';
import { init } from 'emoji-mart';
import Home from "./pages/home/Home";
init({ data })
function App() {
    return (
        <div
            className="App" >
            {/* <div
                className="blur"
                style={
                    {
                        top: '-18%', right
                            :
                            '0'
                    }
                }></div> */}
            {/* <div
                className="blur"
                style={
                    {
                        top: '36%', left
                            :
                            '-8rem'
                    }
                }></div> */}
            <Home />
            {/* <Profile /> */}
            {/*   <Auth /> */}
        </div>
    )
        ;
}

export default App;
