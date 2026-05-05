# ============================================
# Password Manager - Development Mode
# Auto Build & Auto Reload with Spring DevTools
# ============================================

Write-Host ""
Write-Host "====================================" -ForegroundColor Cyan
Write-Host "Password Manager - Development Mode" -ForegroundColor Cyan
Write-Host "====================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Starting application with auto-reload..." -ForegroundColor Green
Write-Host "The app will automatically restart when you:" -ForegroundColor Yellow
Write-Host "  - Change Java files" -ForegroundColor Yellow
Write-Host "  - Change HTML/CSS/JS files in src/main/resources" -ForegroundColor Yellow
Write-Host "  - Change properties files" -ForegroundColor Yellow
Write-Host ""
Write-Host "Open your browser at: " -NoNewline
Write-Host "http://localhost:8080" -ForegroundColor Cyan
Write-Host "Press Ctrl+C to stop the application" -ForegroundColor Yellow
Write-Host ""
Write-Host "====================================" -ForegroundColor Cyan
Write-Host ""

# Navigate to project directory
Set-Location $PSScriptRoot

# Run the application with auto-reload
& ".\mvnw.cmd" spring-boot:run

