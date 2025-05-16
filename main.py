import streamlit as st
from components.file_upload import render_file_upload
from components.chat import render_chat_interface
from components.visualization import render_analysis_results
from components.progress_analytics import render_progress_analytics
from components.smart_quiz import render_smart_quiz_interface
from utils.progress_tracker import ProgressTracker
import os
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# Configure Streamlit page
st.set_page_config(
    page_title="Study Companion AI",
    page_icon="📚",
    layout="wide",
    menu_items={
        'Get Help': 'https://github.com/Sawantparth',
        'Report a bug': "https://github.com/Sawantparth/StudyHelper",
        'About': "Study Companion AI - Your intelligent study partner for exam preparation."
    }
)

# Add custom CSS
st.markdown("""
<style>
    .stApp {
        max-width: 1200px;
        margin: 0 auto;
    }
    .st-emotion-cache-1v0mbdj {
        width: 100%;
    }
    .css-1p05t8e {
        border-radius: 10px;
        padding: 1rem;
        background-color: rgba(255, 255, 255, 0.1);
    }
    .st-emotion-cache-1wrcr25 {
        margin-bottom: 2rem;
    }
</style>
""", unsafe_allow_html=True)

def initialize_session_state():
    """Initialize session state variables."""
    if 'chat_history' not in st.session_state:
        st.session_state.chat_history = []
    if 'analyzed_data' not in st.session_state:
        st.session_state.analyzed_data = None
    if 'uploaded_files' not in st.session_state:
        st.session_state.uploaded_files = []
    if 'error_log' not in st.session_state:
        st.session_state.error_log = []
    if 'progress_tracker' not in st.session_state:
        st.session_state.progress_tracker = ProgressTracker()
    if 'current_quiz' not in st.session_state:
        st.session_state.current_quiz = None
    if 'study_streak' not in st.session_state:
        st.session_state.study_streak = 0
    if 'last_study_date' not in st.session_state:
        st.session_state.last_study_date = None

def main():
    # Initialize session state
    initialize_session_state()

    # Sidebar
    with st.sidebar:
        st.image("assets/logo.png", use_column_width=True)
        st.title("Study Companion AI")
        
        # Study streak
        if st.session_state.study_streak > 0:
            st.success(f"🔥 {st.session_state.study_streak} Day Streak!")
        
        # Quick stats
        if st.session_state.uploaded_files:
            st.metric("Materials Uploaded", len(st.session_state.uploaded_files))
        if hasattr(st.session_state, 'mastery_scores'):
            avg_mastery = sum(
                sum(data["scores"]) / len(data["scores"]) 
                for data in st.session_state.mastery_scores.values()
            ) / len(st.session_state.mastery_scores) if st.session_state.mastery_scores else 0
            st.metric("Average Mastery", f"{avg_mastery:.1%}")

    # Main content
    st.title("📚 Study Companion AI")
    st.markdown("""
    Your intelligent study partner for exam preparation. Upload your study materials 
    and get AI-powered insights, personalized quizzes, and track your progress.
    """)

    # Check for required environment variables
    if not os.getenv("GOOGLE_API_KEY"):
        st.error("Missing API configuration. Please set up your environment variables.")
        st.info("Check the README.md file for setup instructions.")
        return

    try:
        # Create tabs for different functionalities
        tab1, tab2, tab3, tab4, tab5 = st.tabs([
            "📤 Upload Materials",
            "💬 Chat Assistant",
            "📊 Analysis",
            "📝 Smart Quiz",
            "📈 Progress"
        ])

        with tab1:
            render_file_upload()

        with tab2:
            if not st.session_state.uploaded_files:
                st.info("Please upload study materials first to get personalized assistance.")
            else:
                render_chat_interface()

        with tab3:
            if not st.session_state.analyzed_data:
                st.info("Upload your study materials to see detailed analysis.")
            else:
                render_analysis_results()

        with tab4:
            if not st.session_state.uploaded_files:
                st.info("Please upload study materials first to generate quizzes.")
            else:
                render_smart_quiz_interface()

        with tab5:
            render_progress_analytics(st.session_state.progress_tracker)

    except Exception as e:
        st.error(f"An unexpected error occurred. Please try refreshing the page. Error: {str(e)}")
        # Log error for debugging
        st.session_state.error_log.append(str(e))

if __name__ == "__main__":
    main()