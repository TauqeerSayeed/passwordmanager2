# Password Manager Application - Development Setup Guide

## 🚀 Quick Start with Auto-Reload

This project is now configured with **Spring Boot DevTools** for automatic build and reload. No need to manually stop and restart the application anymore!

### How to Run

#### Option 1: Using PowerShell Script (Recommended)
```powershell
# Navigate to the project directory and run:
.\run-dev.ps1
```

#### Option 2: Using Batch Script
```cmd
# Double-click or run from CMD:
run-dev.bat
```

#### Option 3: Manual Command
```powershell
cd C:\Users\tauqe\IdeaProjects\passwordmanager2
.\mvnw spring-boot:run
```

### Access the Application
Once the application starts, open your browser and navigate to:
```
http://localhost:8080
```

**Login Credentials:**
- Admin: username `admin` / password `qaadmin`
- User: username `user` / password `qauser`

## 🔄 Auto-Reload Features

### What Automatically Reloads?

✅ **Java Files**
- Controllers
- Services
- Models
- Configuration classes

✅ **Template Files**
- HTML files (`src/main/resources/templates/`)
- Changes are reflected immediately without reload

✅ **Properties Files**
- `application.properties` changes
- DevTools will trigger a restart

✅ **Static Resources**
- CSS files
- JavaScript files
- Images

### How It Works

1. **Detects Changes**: Spring Boot DevTools monitors your source files for changes
2. **Automatic Compilation**: Maven automatically compiles changed files
3. **Smart Restart**: Only restarts when necessary (uses classloader magic)
4. **Cache Disabled**: Thymeleaf template caching is disabled so HTML changes are instant

### Performance Tips

- **Restart Time**: ~2-3 seconds for most changes
- **Poll Interval**: Set to 2 seconds (configurable in `application.properties`)
- **Quiet Period**: 1 second delay before restart (prevents multiple restarts)

## 📝 Configuration Details

All auto-reload settings are configured in `src/main/resources/application.properties`:

```properties
# Enable DevTools for auto-reload
spring.devtools.restart.enabled=true

# Enable LiveReload server
spring.devtools.livereload.enabled=true

# Disable Thymeleaf caching for live reload
spring.thymeleaf.cache=false

# Restart delay (in milliseconds)
spring.devtools.restart.poll-interval=2000ms
spring.devtools.restart.quiet-period=1000ms
```

## 🛠️ Dependencies Added

```xml
<!-- Spring Boot DevTools -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

## ⚠️ Things to Know

- DevTools is **only active in development mode**
- It's automatically excluded in production builds
- The `fork=true` option in Maven plugin enables proper DevTools functionality
- Watch the console for "Started Application" message after changes

## 🐛 Troubleshooting

### App doesn't restart after changes?
- Make sure IntelliJ/IDE is set to auto-compile on save
- Check if `spring.devtools.restart.enabled=true` in properties
- Restart the application manually once

### Changes not showing in browser?
- Clear browser cache (Ctrl+Shift+Delete)
- If HTML file changed, refresh the page (Ctrl+R)
- LiveReload might need browser extension for automatic refresh

### Port Already in Use?
```powershell
# Find process using port 8080
Get-NetTCPConnection -LocalPort 8080
# Kill the process if needed
Stop-Process -Id <PID> -Force
```

## 📦 Project Structure

```
passwordmanager2/
├── src/
│   ├── main/
│   │   ├── java/com/example/passwordmanager/
│   │   │   ├── controller/     # Auto-reloads on change
│   │   │   ├── model/          # Auto-reloads on change
│   │   │   ├── service/        # Auto-reloads on change
│   │   │   └── SecurityConfig/ # Auto-reloads on change
│   │   └── resources/
│   │       ├── templates/      # Instant reload (no restart)
│   │       └── application.properties
│   └── test/
├── pom.xml
├── run-dev.ps1          # PowerShell script to run dev mode
├── run-dev.bat          # Batch script to run dev mode
└── README.md
```

## 💡 Development Workflow

1. **Start Application**: Run `.\run-dev.ps1`
2. **Make Changes**: Edit Java, HTML, CSS, or properties files
3. **Save File**: Ctrl+S in your editor
4. **Watch Console**: See auto-restart messages (usually < 3 seconds)
5. **Refresh Browser**: See your changes immediately
6. **Repeat**: Continue developing without stopping the app

## 📚 Useful Commands

```powershell
# Build the project
.\mvnw clean build

# Run tests
.\mvnw test

# View all available Maven commands
.\mvnw help:describe
```

## ✨ What's Been Added for Auto-Reload

✅ Spring Boot DevTools dependency in `pom.xml`  
✅ `fork=true` in Maven plugin configuration  
✅ DevTools properties configured in `application.properties`  
✅ Thymeleaf caching disabled for live template updates  
✅ `run-dev.ps1` and `run-dev.bat` scripts for easy startup  

Enjoy hassle-free development! 🎉

