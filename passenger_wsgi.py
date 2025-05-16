import os
import sys

# Add your project directory to Python path
path = '/home/YOUR_PYTHONANYWHERE_USERNAME/StudyHelper'
if path not in sys.path:
    sys.path.append(path)

# Set environment variables
os.environ['GOOGLE_API_KEY'] = 'your_api_key_here'

# Import your Streamlit app
import streamlit.web.bootstrap as bootstrap
from streamlit.web.server import Server
from streamlit.config import get_config_options

def application(environ, start_response):
    # Configure Streamlit
    config_options = get_config_options()
    config_options["server.address"] = "0.0.0.0"
    config_options["server.port"] = 8501
    config_options["browser.serverAddress"] = "your_pythonanywhere_domain.pythonanywhere.com"
    
    # Start Streamlit
    bootstrap.run("main.py", "", [], [])
    
    # Standard WSGI response
    start_response('200 OK', [('Content-Type', 'text/html')])
    return [b"Streamlit app is running"] 