@echo off
REM ============================================
REM Password Manager - Development Mode
REM Auto Build & Auto Reload with Spring DevTools
REM ============================================
echo.
echo ====================================
echo Password Manager - Development Mode
echo ====================================
echo.
echo Starting application with auto-reload...
echo The app will automatically restart when you:
echo   - Change Java files
echo   - Change HTML/CSS/JS files in src/main/resources
echo   - Change properties files
echo.
echo Open your browser at: http://localhost:8080
echo Press Ctrl+C to stop the application
echo.
echo ====================================
echo.

cd /d "%~dp0"
call mvnw spring-boot:run

pause

