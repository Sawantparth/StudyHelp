# Study Companion AI 📚

An intelligent study assistant powered by AI that helps students optimize their learning experience through personalized quizzes, smart content analysis, and progress tracking.

![Study Companion AI](assets/logo.png)

## Features 🌟

- **AI-Powered Chat Assistant** 
  - Context-aware responses
  - Semantic search capabilities
  - Citation tracking
  - Confidence scoring

- **Smart Quiz System** 
  - Adaptive difficulty levels
  - Spaced repetition scheduling
  - Multiple question types
  - Performance analytics

- **Study Material Analysis**
  - Content summarization
  - Topic extraction
  - Key concepts identification
  - Learning path recommendations

- **Progress Tracking**
  - Topic-based mastery tracking
  - Learning progression analysis
  - Performance visualization
  - Study streak monitoring

## Technology Stack 🛠️

- **Frontend**: Streamlit
- **AI/ML**: 
  - Google's Gemini Pro
  - Sentence Transformers
  - Natural Language Processing
- **Data Visualization**: Plotly
- **Development Tools**:
  - Python 3.8+
  - pytest for testing
  - black for code formatting
  - flake8 for linting

## Prerequisites 📋

- Python 3.8 or higher
- Google API key for Gemini Pro
- Git (for cloning the repository)

## Installation 🔧

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sawantparth/StudyHelper.git
   cd StudyHelper
   ```

2. **Create and activate a virtual environment**
   ```bash
   # Windows
   python -m venv venv
   .\venv\Scripts\activate

   # Linux/MacOS
   python3 -m venv venv
   source venv/bin/activate
   ```

3. **Install dependencies**
   ```bash
   pip install -r requirements.txt
   ```

4. **Set up environment variables**
   Create a `.env` file in the root directory and add your Google API key:
   ```
   GOOGLE_API_KEY=your_api_key_here
   ```

## Running the Application 🚀

1. **Start the application**
   ```bash
   streamlit run main.py
   ```

2. **Access the web interface**
   Open your browser and navigate to:
   ```
   http://localhost:8501
   ```

## Usage Guide 📖

1. **Upload Study Materials**
   - Navigate to the "Upload Materials" tab
   - Drag and drop your study materials (PDF, TXT, DOCX)
   - The system will process and analyze your content

2. **Chat with AI Assistant**
   - Go to the "Chat Assistant" tab
   - Ask questions about your study materials
   - Get context-aware responses with citations

3. **Generate Quizzes**
   - Visit the "Smart Quiz" tab
   - Choose difficulty level or use adaptive mode
   - Take quizzes and track your progress

4. **Track Progress**
   - Check the "Progress" tab
   - View topic mastery scores
   - Monitor learning progression
   - Check study streaks

## Development Setup 👩‍💻

1. **Install development dependencies**
   ```bash
   pip install -r requirements-dev.txt
   ```

2. **Set up pre-commit hooks**
   ```bash
   pre-commit install
   ```

3. **Run tests**
   ```bash
   pytest
   ```

4. **Code formatting**
   ```bash
   black .
   ```

## Contributing 🤝

1. Fork the repository
2. Create a new branch (`git checkout -b feature/improvement`)
3. Make your changes
4. Run tests (`pytest`)
5. Commit your changes (`git commit -am 'Add new feature'`)
6. Push to the branch (`git push origin feature/improvement`)
7. Create a Pull Request

## Troubleshooting 🔍

### Common Issues

1. **API Key Issues**
   - Ensure your Google API key is correctly set in the `.env` file
   - Verify the API key has necessary permissions

2. **Installation Problems**
   - Make sure you're using Python 3.8+
   - Try upgrading pip: `pip install --upgrade pip`
   - Check for conflicting dependencies

3. **Performance Issues**
   - Clear browser cache
   - Restart the application
   - Check system resources

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact 📧

- **Developer**: Parth Sawant
- **GitHub**: [@Sawantparth](https://github.com/Sawantparth)
- **Project Link**: [https://github.com/Sawantparth/StudyHelper](https://github.com/Sawantparth/StudyHelper)

## Acknowledgments 🙏

- Google Generative AI team for Gemini Pro
- Streamlit team for the amazing framework
- All contributors and users of the project

---

Made with ❤️ by Parth Sawant 