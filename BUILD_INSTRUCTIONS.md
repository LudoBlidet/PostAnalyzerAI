### 🚀 خطوات بناء APK

#### المتطلبات:
1. ✅ Android Studio (2024.1 أو أحدث)
2. ✅ Android SDK 35
3. ✅ Java 17+
4. ✅ مفتاح Gemini API

#### الخطوات:

**1. إعداد مفتاح Gemini:**
```properties
# في ملف app/local.properties
GEMINI_API_KEY=YOUR_KEY_HERE
```

**2. بناء التطبيق:**

**طريقة 1: من Android Studio**
- Build → Build Bundle(s) / APK(s) → Build APKs(s)
- اختر Release أو Debug
- سيتم حفظ APK في: `app/build/outputs/apk/`

**طريقة 2: من Terminal:**
```bash
# Debug APK
./gradlew assembleDebug

# Release APK (يتطلب توقيع)
./gradlew assembleRelease
```

**3. تشغيل على جهاز:**
```bash
# Install Debug APK
adb install app/build/outputs/apk/debug/app-debug.apk

# أو من Android Studio
Run → Run 'app'
```

#### 📁 موقع APK:
- **Debug**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release**: `app/build/outputs/apk/release/app-release.apk`

#### ✅ اختبار التطبيق:
1. افتح التطبيق
2. اختر "تحليل نص"
3. اكتب منشور تجريبي:
   ```
   منتج جديد رائع! يحسّن إنتاجيتك بنسبة 300%.
   جودة عالية وسعر منخفض. لا تفوّت هذه الفرصة!
   #حصري #محدود #الآن
   ```
4. اختر المنصة (TikTok/Instagram/Facebook)
5. اضغط "تحليل"
6. شاهد النتائج
7. جرب "مشاركة" و "حفظ"
8. افتح "السجل" لترى التحليلات السابقة

#### 🐛 استكشاف الأخطاء:

**خطأ: Cannot resolve symbol**
- Solution: Build → Clean Project → Rebuild Project

**خطأ: GEMINI_API_KEY not found**
- Solution: تأكد من أن `local.properties` موجود مع المفتاح الصحيح

**خطأ: Cannot connect to API**
- Solution: تأكد من الاتصال بالإنترنت والمفتاح صحيح

**خطأ: Gradle sync failed**
- Solution: File → Sync Now → إذا استمر، حذف `.gradle` folder

#### 📊 حجم APK:
- Debug: ~15-20 MB
- Release (مضغوط): ~10-15 MB

#### 🔐 توقيع Release APK:
```bash
# إنشاء keystore
keytool -genkey -v -keystore release.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias release

# في build.gradle.kts
signingConfigs {
    release {
        storeFile = file("release.keystore")
        storePassword = "your_password"
        keyAlias = "release"
        keyPassword = "your_password"
    }
}

buildTypes {
    release {
        signingConfig = signingConfigs.release
    }
}
```

#### ✨ الميزات المتضمنة:
✅ تحليل نص/صورة/فيديو
✅ دعم 3 منصات (TikTok, Instagram, Facebook)
✅ حفظ محلي بقاعدة بيانات Room
✅ مشاركة النتائج عبر Intent
✅ تقييم من 0-100 مع شريط تقدم
✅ نقاط قوة/ضعف واقتراحات تفصيلية
✅ واجهة Material 3 احترافية
✅ دعم RTL (عربي)
✅ معالجة شاملة للأخطاء
✅ UI حديث مع Compose

---

**تم التطوير بواسطة**: BliDaoui 👨‍💻
**المحرك**: Google Gemini 2.0 Flash 🤖
**الإصدار**: 1.0
