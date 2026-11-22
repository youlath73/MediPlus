package com.example.data.local

import com.example.data.local.entity.MedicineEntity
import com.example.domain.model.MedicineCategory

object SeedData {

  private data class ClinicalMonograph(
    val genericName: String,
    val genericNameBn: String,
    val indicationsEn: String,
    val indicationsBn: String,
    val dosageEn: String,
    val dosageBn: String,
    val pharmacologyEn: String,
    val pharmacologyBn: String,
    val contraindicationsEn: String,
    val contraindicationsBn: String,
    val sideEffectsEn: String,
    val sideEffectsBn: String,
    val precautionsEn: String,
    val precautionsBn: String,
  )

  private val paracetamolMono =
    ClinicalMonograph(
      genericName = "Paracetamol",
      genericNameBn = "প্যারাসিটামল",
      indicationsEn =
        "Fever, headache, toothache, migraine, muscle ache, neuralgia, dysmenorrhea, and post-vaccination pyrexia.",
      indicationsBn =
        "জ্বর, মাথাব্যথা, দাঁতের ব্যথা, মাইগ্রেন, মাংসপেশীর ব্যথা, স্নায়ুবিক ব্যথা এবং টিকা পরবর্তী জ্বর।",
      dosageEn =
        "Adults: 1–2 tablets (500–1000 mg) every 4–6 hours. Maximum 4 g (8 tablets) in 24 hours. Can be taken with or without food.",
      dosageBn =
        "প্রাপ্তবয়স্ক: ১–২টি ট্যাবলেট (৫০০–১০০০ মি.গ্রা.) প্রতি ৪–৬ ঘণ্টা পর পর। ২৪ ঘণ্টায় সর্বোচ্চ ৪ গ্রাম (৮টি ট্যাবলেট)। খাবারের আগে বা পরে খাওয়া যায়।",
      pharmacologyEn =
        "Inhibits prostaglandin synthesis in the central nervous system (COX-3 pathway) producing antipyretic and analgesic effects without significant peripheral anti-inflammatory action.",
      pharmacologyBn =
        "কেন্দ্রীয় স্নায়ুতন্ত্রে প্রোস্টাগ্ল্যান্ডিন সংশ্লেষণ বাধা দিয়ে জ্বর কমায় এবং ব্যথা উপশম করে।",
      contraindicationsEn =
        "Known hypersensitivity to paracetamol; severe active hepatic impairment.",
      contraindicationsBn =
        "প্যারাসিটামলের প্রতি অতিসংবেদনশীলতা বা গুরুতর যকৃতের রোগে প্রতিনির্দেশিত।",
      sideEffectsEn =
        "Rare at therapeutic doses; occasional mild nausea, skin rash, or hematological reactions. Overdose may cause severe hepatotoxicity.",
      sideEffectsBn =
        "নির্ধারিত মাত্রায় পার্শ্বপ্রতিক্রিয়া বিরল; কদাচিৎ বমি ভাব বা ত্বকে ফুসকুড়ি হতে পারে। অতিরিক্ত মাত্রায় যকৃতের ক্ষতি হতে পারে।",
      precautionsEn =
        "Use with caution in chronic alcoholism, malnutrition, renal impairment, or concurrent hepatotoxic drugs.",
      precautionsBn =
        "দীর্ঘমেয়াদী অ্যালকোহল সেবন, অপুষ্টি, কিডনি বা লিভারের সমস্যায় সতর্কতার সাথে ব্যবহার করুন।",
    )

  private val omeprazoleMono =
    ClinicalMonograph(
      genericName = "Omeprazole",
      genericNameBn = "ওমিপ্রাজল",
      indicationsEn =
        "Gastric and duodenal ulcers, Gastroesophageal Reflux Disease (GERD), NSAID-associated ulcers, Zollinger-Ellison syndrome, and Helicobacter pylori eradication.",
      indicationsBn =
        "গ্যাস্ট্রিক ও ডিওডেনাল আলসার, গ্যাস্ট্রোইসোফেজিয়াল রিফ্লাক্স ডিজিজ (GERD), এসিডিটি এবং বুকজ্বালা।",
      dosageEn =
        "Adults: 20 mg once daily 30–60 minutes before breakfast for 4–8 weeks. Severe erosive esophagitis: 40 mg daily.",
      dosageBn =
        "প্রাপ্তবয়স্ক: দৈনিক ২০ মি.গ্রা. সকালের নাস্তার ৩০–৬০ মিনিট আগে ৪–৮ সপ্তাহ। তীব্র ক্ষেত্রে দৈনিক ৪০ মি.গ্রা.।",
      pharmacologyEn =
        "Proton pump inhibitor (PPI) that irreversibly blocks the H+/K+-ATPase enzyme system in gastric parietal cells, suppressing basal and stimulated gastric acid secretion.",
      pharmacologyBn =
        "প্রোটন পাম্প ইনহিবিটর (PPI) যা পাকস্থলীর প্যারাইটাল কোষের H+/K+-ATPase এনজাইম ব্লক করে এসিড নিঃসরণ কমায়।",
      contraindicationsEn =
        "Hypersensitivity to omeprazole or substituted benzimidazoles; co-administration with rilpivirine or nelfinavir.",
      contraindicationsBn =
        "ওমিপ্রাজল বা বেনজিমিডাজল গ্রুপের ওষুধের প্রতি সংবেদনশীলতায় প্রতিনির্দেশিত।",
      sideEffectsEn =
        "Headache, abdominal pain, diarrhea, constipation, flatulence, and nausea. Long-term use may lower magnesium and vitamin B12.",
      sideEffectsBn =
        "মাথাব্যথা, পেট ব্যথা, ডায়রিয়া, কোষ্ঠকাঠিন্য, গ্যাস ও বমি ভাব হতে পারে।",
      precautionsEn =
        "Rule out gastric malignancy before starting therapy. Monitor bone mineral density and magnesium during prolonged therapy.",
      precautionsBn =
        "দীর্ঘদিন ব্যবহারের আগে পাকস্থলীর গুরুতর রোগ না থাকার বিষয়ে নিশ্চিত হোন; চিকিৎসকের পরামর্শ ছাড়া দীর্ঘমেয়াদী সেবন করবেন না।",
    )

  private val esomeprazoleMono =
    ClinicalMonograph(
      genericName = "Esomeprazole",
      genericNameBn = "ইসোমিপ্রাজল",
      indicationsEn =
        "Erosive reflux esophagitis, symptomatic GERD, prevention of NSAID-induced gastric ulcers, and H. pylori eradication regimens.",
      indicationsBn =
        "ইরোসিভ রিফ্লাক্স ইসোফ্যাগাইটিস, তীব্র বুকজ্বালা (GERD), পেপটিক আলসার এবং ব্যথানাশকের কারণে সৃষ্ট গ্যাস্ট্রিক প্রতিরোধে।",
      dosageEn =
        "20–40 mg once daily taken at least 30–60 minutes before the first meal of the day. Swallow capsule/tablet whole.",
      dosageBn =
        "২০–৪০ মি.গ্রা. প্রতিদিন একবার খাবারের অন্তত ৩০–৬০ মিনিট আগে সেব্য। ক্যাপসুল/ট্যাবলেট না ভেঙে গিলে খাবেন।",
      pharmacologyEn =
        "S-isomer of omeprazole offering slower hepatic metabolism, higher systemic bioavailability, and more consistent 24-hour intragastric pH control.",
      pharmacologyBn =
        "ওমিপ্রাজলের এস-আইসোমার, যা দ্রুত ও দীর্ঘস্থায়ীভাবে পাকস্থলীর অতিরিক্ত এসিড নিয়ন্ত্রণ করে।",
      contraindicationsEn = "Known hypersensitivity to esomeprazole or any PPI component.",
      contraindicationsBn = "ইসোমিপ্রাজল বা অন্য কোনো পিপিআই-এর প্রতি অ্যালার্জি থাকলে সেবন নিষিদ্ধ।",
      sideEffectsEn = "Mild headache, nausea, dry mouth, dizziness, or mild abdominal bloating.",
      sideEffectsBn = "মাঝে মাঝে মাথাব্যথা, বমি ভাব, মুখ শুকিয়ে যাওয়া বা পেট ফাঁপা হতে পারে।",
      precautionsEn =
        "Avoid concomitant clopidogrel unless advised by a cardiologist; consult physician during pregnancy.",
      precautionsBn =
        "ক্লোপিডোগ্রেল সেবনকারী রোগী ও গর্ভবতী মায়েদের ক্ষেত্রে চিকিৎসকের পরামর্শ আবশ্যক।",
    )

  private val fexofenadineMono =
    ClinicalMonograph(
      genericName = "Fexofenadine Hydrochloride",
      genericNameBn = "ফেক্সোফেনাডিন হাইড্রোক্লোরাইড",
      indicationsEn =
        "Seasonal allergic rhinitis (sneezing, rhinorrhea, itchy eyes/nose) and chronic idiopathic urticaria (skin hives and itching).",
      indicationsBn =
        "মৌসুমি অ্যালার্জিক রাইনাইটিস (হাঁচি, নাক দিয়ে পানি পড়া, চোখ চুলকানো) এবং দীর্ঘস্থায়ী আর্টিকারিয়া বা ত্বকের চুলকানি।",
      dosageEn =
        "Adults & children ≥12 years: 120 mg once daily for allergic rhinitis, or 180 mg once daily for chronic urticaria with water (avoid fruit juices).",
      dosageBn =
        "প্রাপ্তবয়স্ক ও ১২ বছরের বেশি: অ্যালার্জির জন্য ১২০ মি.গ্রা. দিনে একবার এবং তীব্র ত্বকের চুলকানিতে ১৮০ মি.গ্রা. দিনে একবার। ফলের রসের সাথে খাবেন না।",
      pharmacologyEn =
        "Second-generation non-sedating selective peripheral H1-receptor antagonist that does not cross the blood-brain barrier appreciably.",
      pharmacologyBn =
        "দ্বিতীয় প্রজন্মের ঘুমহীন অ্যান্টিহিস্টামিন যা পেরিফেরাল H1-রিসেপ্টর ব্লক করে অ্যালার্জি উপশম করে।",
      contraindicationsEn = "Hypersensitivity to fexofenadine or excipients.",
      contraindicationsBn = "ফেক্সোফেনাডিনের প্রতি অতিসংবেদনশীলতায় প্রতিনির্দেশিত।",
      sideEffectsEn = "Uncommon: mild headache, drowsiness in sensitive individuals, or mild fatigue.",
      sideEffectsBn = "কদাচিৎ হালকা মাথাব্যথা, সামান্য ঝিমুনি বা ক্লান্তি অনুভূত হতে পারে।",
      precautionsEn =
        "Avoid taking with orange, grapefruit, or apple juice as they reduce absorption. Separate from aluminum/magnesium antacids by 2 hours.",
      precautionsBn =
        "অ্যান্টাসিড বা ফলের রসের সাথে একত্রে সেবন করলে শোষণ কমে যায়; ২ ঘণ্টা বিরতি দিন।",
    )

  private val montelukastMono =
    ClinicalMonograph(
      genericName = "Montelukast Sodium",
      genericNameBn = "মন্টেলুকাস্ট সোডিয়াম",
      indicationsEn =
        "Prophylaxis and chronic treatment of asthma, prevention of exercise-induced bronchoconstriction, and relief of allergic rhinitis.",
      indicationsBn =
        "হাঁপানি (অ্যাজমা) প্রতিরোধ ও দীর্ঘমেয়াদী চিকিৎসা, ব্যায়ামজনিত শ্বাসকষ্ট এবং অ্যালার্জিক রাইনাইটিস উপশম।",
      dosageEn =
        "Adults & adolescents ≥15 years: One 10 mg tablet once daily in the evening, with or without food.",
      dosageBn =
        "প্রাপ্তবয়স্ক (১৫+ বছর): প্রতিদিন সন্ধ্যায় ১টি ১০ মি.গ্রা. ট্যাবলেট খাবারের সাথে বা খালি পেটে।",
      pharmacologyEn =
        "Selective leukotriene receptor antagonist (LTRA) that blocks cysteinyl leukotriene CysLT1 receptors in the airways, reducing bronchoconstriction and mucosal inflammation.",
      pharmacologyBn =
        "সিলেক্টিভ লিউকোট্রিন রিসেপ্টর অ্যান্টাগনিস্ট যা শ্বাসনালীর প্রদাহ ও সংকোচন কমিয়ে শ্বাসপ্রশ্বাস সহজ করে।",
      contraindicationsEn = "Hypersensitivity to montelukast; not indicated for acute asthma attacks.",
      contraindicationsBn =
        "হঠাৎ তীব্র হাঁপানি আক্রমণের তাৎক্ষণিক উপশমের জন্য ব্যবহার্য নয়; অতিসংবেদনশীলতায় নিষিদ্ধ।",
      sideEffectsEn =
        "Headache, upper respiratory symptoms, abdominal discomfort, and rare mood/sleep changes (vivid dreams, agitation).",
      sideEffectsBn =
        "মাথাব্যথা, পেটে অস্বস্তি এবং বিরল ক্ষেত্রে ঘুমের সমস্যা বা মেজাজের পরিবর্তন হতে পারে।",
      precautionsEn =
        "Do not abruptly replace inhaled or oral corticosteroids. Report any unusual neuropsychiatric symptoms promptly.",
      precautionsBn =
        "অস্বাভাবিক মানসিক অস্থিরতা বা ঘুমের ব্যাঘাত দেখা দিলে দ্রুত চিকিৎসকের পরামর্শ নিন।",
    )

  private val azithromycinMono =
    ClinicalMonograph(
      genericName = "Azithromycin",
      genericNameBn = "অ্যাজিথ্রোমাইসিন",
      indicationsEn =
        "Upper and lower respiratory tract infections (pharyngitis, tonsillitis, bronchitis, community-acquired pneumonia), acute otitis media, skin infections, and uncomplicated typhoid.",
      indicationsBn =
        "শ্বাসনালীর সংক্রমণ (টনসিলাইটিস, ব্রঙ্কাইটিস, নিউমোনিয়া), কানের সংক্রমণ, ত্বকের সংক্রমণ এবং টাইফয়েড।",
      dosageEn =
        "Adults: 500 mg once daily for 3 days (or 500 mg Day 1 followed by 250 mg once daily on Days 2–5). Complete full prescribed course.",
      dosageBn =
        "প্রাপ্তবয়স্ক: ৫০০ মি.গ্রা. দিনে একবার টানা ৩ দিন (অথবা চিকিৎসকের নির্ধারিত কোর্স সম্পূর্ণ করুন)।",
      pharmacologyEn =
        "Azalide macrolide antibiotic that binds to the 50S ribosomal subunit of susceptible bacteria, inhibiting RNA-dependent protein synthesis.",
      pharmacologyBn =
        "ম্যাক্রোলাইড অ্যান্টিবায়োটিক যা ব্যাকটেরিয়ার 50S রাইবোসোমাল সাবইউনিটে যুক্ত হয়ে প্রোটিন তৈরি বন্ধ করে।",
      contraindicationsEn =
        "History of cholestatic jaundice or hepatic dysfunction associated with prior azithromycin use; macrolide hypersensitivity.",
      contraindicationsBn =
        "ম্যাক্রোলাইড অ্যান্টিবায়োটিকে অ্যালার্জি বা পূর্বে অ্যাজিথ্রোমাইসিনজনিত লিভার সমস্যায় প্রতিনির্দেশিত।",
      sideEffectsEn = "Diarrhea, nausea, abdominal cramping, loose stools, and mild dyspepsia.",
      sideEffectsBn = "পাতলা পায়খানা, বমি ভাব, পেট কামড়ানো বা হজমে অস্বস্তি হতে পারে।",
      precautionsEn =
        "Use strictly on a registered physician's prescription. Caution in patients with QT prolongation or severe hepatic disease.",
      precautionsBn =
        "চিকিৎসকের ব্যবস্থাপত্র ছাড়া অ্যান্টিবায়োটিক সেবন করবেন না এবং অ্যান্টিবায়োটিক রেজিস্ট্যান্স এড়াতে পূর্ণ কোর্স শেষ করুন।",
    )

  private fun buildMedicine(
    id: Int,
    brand: String,
    brandBn: String,
    company: String,
    companyBn: String,
    strength: String,
    form: String,
    formBn: String,
    category: MedicineCategory,
    price: Double,
    pack: String,
    packBn: String,
    popularity: Int,
    mono: ClinicalMonograph,
  ) =
    MedicineEntity(
      id = id,
      brandName = brand,
      brandNameBn = brandBn,
      genericName = mono.genericName,
      genericNameBn = mono.genericNameBn,
      companyName = company,
      companyNameBn = companyBn,
      strength = strength,
      dosageForm = form,
      dosageFormBn = formBn,
      category = category.storageKey,
      priceMrp = price,
      packUnit = pack,
      packUnitBn = packBn,
      popularityScore = popularity,
      indicationsEn = mono.indicationsEn,
      indicationsBn = mono.indicationsBn,
      dosageEn = mono.dosageEn,
      dosageBn = mono.dosageBn,
      pharmacologyEn = mono.pharmacologyEn,
      pharmacologyBn = mono.pharmacologyBn,
      contraindicationsEn = mono.contraindicationsEn,
      contraindicationsBn = mono.contraindicationsBn,
      sideEffectsEn = mono.sideEffectsEn,
      sideEffectsBn = mono.sideEffectsBn,
      precautionsEn = mono.precautionsEn,
      precautionsBn = mono.precautionsBn,
    )

  val medicines: List<MedicineEntity> =
    listOf(
      // --- Paracetamol 500mg group (same generic comparison) ---
      buildMedicine(
        id = 1,
        brand = "Napa",
        brandBn = "নাপা",
        company = "Beximco Pharmaceuticals Ltd.",
        companyBn = "বেক্সিমকো ফার্মাসিউটিক্যালস লি.",
        strength = "500 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 1.20,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 100,
        mono = paracetamolMono,
      ),
      buildMedicine(
        id = 2,
        brand = "Ace",
        brandBn = "এইস",
        company = "Square Pharmaceuticals PLC",
        companyBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "500 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 1.20,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 96,
        mono = paracetamolMono,
      ),
      buildMedicine(
        id = 3,
        brand = "Renova",
        brandBn = "রেনোভা",
        company = "Opsonin Pharma Ltd.",
        companyBn = "অপসোনিন ফার্মা লি.",
        strength = "500 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 1.15,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 85,
        mono = paracetamolMono,
      ),
      MedicineEntity(
        id = 4,
        brandName = "Napa Extra",
        brandNameBn = "নাপা এক্সট্রা",
        genericName = "Paracetamol + Caffeine",
        genericNameBn = "প্যারাসিটামল + ক্যাফেইন",
        companyName = "Beximco Pharmaceuticals Ltd.",
        companyNameBn = "বেক্সিমকো ফার্মাসিউটিক্যালস লি.",
        strength = "500 mg + 65 mg",
        dosageForm = "Tablet",
        dosageFormBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC.storageKey,
        priceMrp = 2.50,
        packUnit = "per tablet",
        packUnitBn = "প্রতি পিস",
        popularityScore = 94,
        indicationsEn =
          "Migraine, tension headache, toothache, neuralgia, rheumatic pain, and fever with body fatigue.",
        indicationsBn =
          "মাইগ্রেন, টেনশন মাথাব্যথা, দাঁতের ব্যথা, স্নায়ুবিক ব্যথা ও জ্বরসহ শারীরিক অবসাদ।",
        dosageEn =
          "Adults & children ≥12 years: 1–2 tablets every 4–6 hours. Do not exceed 8 tablets in 24 hours; avoid excessive coffee/tea.",
        dosageBn =
          "প্রাপ্তবয়স্ক ও ১২+ বছর: ১–২টি ট্যাবলেট প্রতি ৪–৬ ঘণ্টায়। ২৪ ঘণ্টায় ৮টির বেশি নয়।",
        pharmacologyEn =
          "Paracetamol provides central analgesia and antipyresis while caffeine potentiates analgesic efficacy and relieves vascular headache.",
        pharmacologyBn =
          "প্যারাসিটামল ব্যথা ও জ্বর কমায় এবং ক্যাফেইন ব্যথানাশক কার্যকারিতা বৃদ্ধি করে।",
        contraindicationsEn =
          "Hypersensitivity to paracetamol or caffeine; severe hypertension or cardiac arrhythmia.",
        contraindicationsBn =
          "প্যারাসিটামল বা ক্যাফেইনে অতিসংবেদনশীলতা এবং তীব্র উচ্চ রক্তচাপে সতর্কতা।",
        sideEffectsEn =
          "Restlessness, insomnia, mild palpitations if combined with dietary caffeine.",
        sideEffectsBn =
          "অস্থিরতা, অনিদ্রা বা অতিরিক্ত চা-কফি খেলে বুক ধড়ফড় করতে পারে।",
        precautionsEn =
          "Limit additional caffeine intake; avoid late-night dosing if sensitive to sleep disturbance.",
        precautionsBn =
          "রাতে ঘুমানোর ঠিক আগে সেবন না করাই ভালো; লিভার রোগে সতর্কতা অবলম্বন করুন।",
      ),
      // --- Omeprazole 20mg group ---
      buildMedicine(
        id = 5,
        brand = "Seclo",
        brandBn = "সেকলো",
        company = "Square Pharmaceuticals PLC",
        companyBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "20 mg",
        form = "Capsule",
        formBn = "ক্যাপসুল",
        category = MedicineCategory.ALLOPATHIC,
        price = 6.00,
        pack = "per capsule",
        packBn = "প্রতি পিস",
        popularity = 98,
        mono = omeprazoleMono,
      ),
      buildMedicine(
        id = 6,
        brand = "Losectil",
        brandBn = "লোসেকটিল",
        company = "Eskayef Pharmaceuticals Ltd.",
        companyBn = "এসকেএফ ফার্মাসিউটিক্যালস লি.",
        strength = "20 mg",
        form = "Capsule",
        formBn = "ক্যাপসুল",
        category = MedicineCategory.ALLOPATHIC,
        price = 5.50,
        pack = "per capsule",
        packBn = "প্রতি পিস",
        popularity = 89,
        mono = omeprazoleMono,
      ),
      buildMedicine(
        id = 7,
        brand = "Omez",
        brandBn = "ওমেজ",
        company = "Incepta Pharmaceuticals Ltd.",
        companyBn = "ইনসেপ্টা ফার্মাসিউটিক্যালস লি.",
        strength = "20 mg",
        form = "Capsule",
        formBn = "ক্যাপসুল",
        category = MedicineCategory.ALLOPATHIC,
        price = 5.00,
        pack = "per capsule",
        packBn = "প্রতি পিস",
        popularity = 83,
        mono = omeprazoleMono,
      ),
      // --- Esomeprazole group ---
      buildMedicine(
        id = 8,
        brand = "Maxpro",
        brandBn = "ম্যাক্সপ্রো",
        company = "Renata PLC",
        companyBn = "রেনাটা পিএলসি",
        strength = "20 mg",
        form = "MUPS Tablet",
        formBn = "মাপস ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 7.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 99,
        mono = esomeprazoleMono,
      ),
      buildMedicine(
        id = 9,
        brand = "Sergel",
        brandBn = "সারজেল",
        company = "Healthcare Pharmaceuticals Ltd.",
        companyBn = "হেলথকেয়ার ফার্মাসিউটিক্যালস লি.",
        strength = "20 mg",
        form = "Capsule",
        formBn = "ক্যাপসুল",
        category = MedicineCategory.ALLOPATHIC,
        price = 7.00,
        pack = "per capsule",
        packBn = "প্রতি পিস",
        popularity = 97,
        mono = esomeprazoleMono,
      ),
      buildMedicine(
        id = 10,
        brand = "Nexum",
        brandBn = "নেক্সাম",
        company = "Square Pharmaceuticals PLC",
        companyBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "20 mg",
        form = "MUPS Tablet",
        formBn = "মাপস ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 7.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 91,
        mono = esomeprazoleMono,
      ),
      buildMedicine(
        id = 11,
        brand = "Maxpro 40",
        brandBn = "ম্যাক্সপ্রো ৪০",
        company = "Renata PLC",
        companyBn = "রেনাটা পিএলসি",
        strength = "40 mg",
        form = "MUPS Tablet",
        formBn = "মাপস ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 10.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 87,
        mono = esomeprazoleMono,
      ),
      // --- Fexofenadine 120mg group ---
      buildMedicine(
        id = 12,
        brand = "Fexo",
        brandBn = "ফেক্সো",
        company = "Square Pharmaceuticals PLC",
        companyBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "120 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 9.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 93,
        mono = fexofenadineMono,
      ),
      buildMedicine(
        id = 13,
        brand = "Telfast",
        brandBn = "টেলফাস্ট",
        company = "Synovia Pharma PLC",
        companyBn = "সিনোভিয়া ফার্মা পিএলসি",
        strength = "120 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 10.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 86,
        mono = fexofenadineMono,
      ),
      // --- Montelukast 10mg group ---
      buildMedicine(
        id = 14,
        brand = "Monas",
        brandBn = "মোনাস",
        company = "ACME Laboratories Ltd.",
        companyBn = "একমি ল্যাবরেটরিজ লি.",
        strength = "10 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 17.50,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 95,
        mono = montelukastMono,
      ),
      buildMedicine(
        id = 15,
        brand = "Montair",
        brandBn = "মন্টেয়ার",
        company = "Incepta Pharmaceuticals Ltd.",
        companyBn = "ইনসেপ্টা ফার্মাসিউটিক্যালস লি.",
        strength = "10 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 16.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 84,
        mono = montelukastMono,
      ),
      // --- Azithromycin 500mg group ---
      buildMedicine(
        id = 16,
        brand = "Zimax",
        brandBn = "জিম্যাক্স",
        company = "Square Pharmaceuticals PLC",
        companyBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "500 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 35.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 92,
        mono = azithromycinMono,
      ),
      buildMedicine(
        id = 17,
        brand = "Azithrocin",
        brandBn = "অ্যাজিথ্রোসিন",
        company = "Beximco Pharmaceuticals Ltd.",
        companyBn = "বেক্সিমকো ফার্মাসিউটিক্যালস লি.",
        strength = "500 mg",
        form = "Tablet",
        formBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC,
        price = 35.00,
        pack = "per tablet",
        packBn = "প্রতি পিস",
        popularity = 82,
        mono = azithromycinMono,
      ),
      // --- Additional Key Bangladeshi Allopathic Medicines ---
      MedicineEntity(
        id = 18,
        brandName = "Cef-3",
        brandNameBn = "সেফ-৩",
        genericName = "Cefixime Trihydrate",
        genericNameBn = "সেফিক্সিম ট্রাইহাইড্রেট",
        companyName = "Square Pharmaceuticals PLC",
        companyNameBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "200 mg",
        dosageForm = "Capsule",
        dosageFormBn = "ক্যাপসুল",
        category = MedicineCategory.ALLOPATHIC.storageKey,
        priceMrp = 35.00,
        packUnit = "per capsule",
        packUnitBn = "প্রতি পিস",
        popularityScore = 88,
        indicationsEn =
          "Uncomplicated urinary tract infections, typhoid fever, pharyngitis, acute bronchitis, and otitis media caused by susceptible organisms.",
        indicationsBn =
          "মূত্রনালীর সংক্রমণ, টাইফয়েড জ্বর, গলার সংক্রমণ, ব্রঙ্কাইটিস এবং কানের সংক্রমণ।",
        dosageEn =
          "Adults: 200 mg every 12 hours or 400 mg once daily for 7–14 days as prescribed.",
        dosageBn =
          "প্রাপ্তবয়স্ক: ২০০ মি.গ্রা. প্রতি ১২ ঘণ্টায় অথবা ৪০০ মি.গ্রা. দিনে একবার ৭–১৪ দিন।",
        pharmacologyEn =
          "Third-generation oral cephalosporin that inhibits bacterial cell wall mucopeptide synthesis.",
        pharmacologyBn =
          "তৃতীয় প্রজন্মের সেফালোস্পোরিন অ্যান্টিবায়োটিক যা ব্যাকটেরিয়ার কোষপ্রাচীর গঠনে বাধা দেয়।",
        contraindicationsEn = "Known allergy to cephalosporins or severe penicillin anaphylaxis.",
        contraindicationsBn = "সেফালোস্পোরিন বা পেনিসিলিনে তীব্র অ্যালার্জি থাকলে নিষিদ্ধ।",
        sideEffectsEn = "Loose stools, mild abdominal pain, nausea, or transient skin rash.",
        sideEffectsBn = "পাতলা পায়খানা, পেট ব্যথা, বমি ভাব বা ত্বকে র‍্যাশ হতে পারে।",
        precautionsEn =
          "Adjust dose in severe renal impairment; monitor for pseudomembranous colitis if severe diarrhea occurs.",
        precautionsBn =
          "কিডনির সমস্যায় ডোজ সমন্বয় প্রয়োজন; চিকিৎসকের নির্দেশ অনুযায়ী পূর্ণ কোর্স শেষ করুন।",
      ),
      MedicineEntity(
        id = 19,
        brandName = "Comet",
        brandNameBn = "কমেট",
        genericName = "Metformin Hydrochloride",
        genericNameBn = "মেটফরমিন হাইড্রোক্লোরাইড",
        companyName = "Square Pharmaceuticals PLC",
        companyNameBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "500 mg",
        dosageForm = "Tablet",
        dosageFormBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC.storageKey,
        priceMrp = 4.00,
        packUnit = "per tablet",
        packUnitBn = "প্রতি পিস",
        popularityScore = 90,
        indicationsEn =
          "First-line management of Type 2 diabetes mellitus, particularly in overweight patients when diet and exercise alone are insufficient.",
        indicationsBn =
          "টাইপ-২ ডায়াবেটিস মেলাইটাস নিয়ন্ত্রণে প্রধান ওষুধ (খাদ্যাভ্যাস ও ব্যায়ামের পাশাপাশি)।",
        dosageEn =
          "Initial: 500 mg once or twice daily with meals to reduce GI upset; titrate gradually up to 2000 mg/day.",
        dosageBn =
          "প্রাথমিক মাত্রা: ৫০০ মি.গ্রা. দিনে ১–২ বার খাবারের সাথে বা পরে সেব্য।",
        pharmacologyEn =
          "Biguanide antihyperglycemic agent that decreases hepatic gluconeogenesis and improves peripheral insulin sensitivity without causing hypoglycemia.",
        pharmacologyBn =
          "বিগুয়ানাইড গ্রুপের ওষুধ যা লিভারের গ্লুকোজ উৎপাদন কমায় এবং ইনসুলিনের কার্যকারিতা বাড়ায়।",
        contraindicationsEn =
          "Severe renal dysfunction (eGFR < 30 mL/min), metabolic acidosis, acute heart failure, or severe dehydration.",
        contraindicationsBn =
          "গুরুতর কিডনি বিকলতা, মেটাবলিক অ্যাসিডোসিস বা তীব্র পানিশূন্যতায় প্রতিনির্দেশিত।",
        sideEffectsEn = "Metallic taste, mild nausea, bloating, or loose stools during early weeks.",
        sideEffectsBn =
          "শুরুতে মুখে ধাতব স্বাদ, হালকা বমি ভাব, পেট ফাঁপা বা পাতলা পায়খানা হতে পারে।",
        precautionsEn =
          "Monitor kidney function and vitamin B12 annually; temporarily hold before iodinated contrast imaging.",
        precautionsBn =
          "নিয়মিত কিডনি ফাংশন ও ভিটামিন বি১২ পরীক্ষা করুন; কনট্রাস্ট এক্স-রের আগে চিকিৎসককে জানান।",
      ),
      MedicineEntity(
        id = 20,
        brandName = "Angilock",
        brandNameBn = "অ্যাঞ্জিলক",
        genericName = "Losartan Potassium",
        genericNameBn = "লোসার্টান পটাশিয়াম",
        companyName = "Square Pharmaceuticals PLC",
        companyNameBn = "স্কয়ার ফার্মাসিউটিক্যালস পিএলসি",
        strength = "50 mg",
        dosageForm = "Tablet",
        dosageFormBn = "ট্যাবলেট",
        category = MedicineCategory.ALLOPATHIC.storageKey,
        priceMrp = 8.00,
        packUnit = "per tablet",
        packUnitBn = "প্রতি পিস",
        popularityScore = 88,
        indicationsEn =
          "Hypertension, diabetic nephropathy in Type 2 diabetes, and stroke risk reduction in hypertensive patients with left ventricular hypertrophy.",
        indicationsBn =
          "উচ্চ রক্তচাপ নিয়ন্ত্রণ এবং ডায়াবেটিক কিডনি রোগ প্রতিরোধ।",
        dosageEn =
          "Usual starting dose: 50 mg once daily; may be adjusted to 25–100 mg daily based on blood pressure response.",
        dosageBn =
          "সাধারণ মাত্রা: ৫০ মি.গ্রা. প্রতিদিন একবার; রক্তচাপ অনুযায়ী চিকিৎসক মাত্রা সমন্বয় করেন।",
        pharmacologyEn =
          "Angiotensin II receptor blocker (ARB) selectively blocking AT1 receptors, causing vasodilation and reduced aldosterone secretion.",
        pharmacologyBn =
          "অ্যাঞ্জিওটেনসিন-২ রিসেপ্টর ব্লকার (ARB) যা রক্তনালী প্রসারিত করে রক্তচাপ স্বাভাবিক রাখে।",
        contraindicationsEn =
          "Pregnancy (fetal toxicity), bilateral renal artery stenosis, or concurrent aliskiren in diabetes.",
        contraindicationsBn =
          "গর্ভাবস্থায় সম্পূর্ণ নিষিদ্ধ; পটাশিয়ামের মাত্রা বেশি হলে সতর্কতা প্রয়োজন।",
        sideEffectsEn = "Dizziness, mild fatigue, nasal congestion, or occasional hyperkalemia.",
        sideEffectsBn = "মাথা ঘোরা, হালকা ক্লান্তি বা রক্তে পটাশিয়াম বেড়ে যাওয়া।",
        precautionsEn =
          "Monitor serum potassium and creatinine; discontinue immediately if pregnancy is detected.",
        precautionsBn =
          "নিয়মিত রক্তচাপ ও পটাশিয়াম পরীক্ষা করুন এবং হঠাৎ ওষুধ বন্ধ করবেন না।",
      ),
      // --- Herbal Medicines (Bangladesh Market) ---
      MedicineEntity(
        id = 21,
        brandName = "Adovas",
        brandNameBn = "অ্যাডোভাস",
        genericName = "Adhatoda Vasica + Ocimum + Glycyrrhiza",
        genericNameBn = "বাসক + তুলসী + যষ্টিমধু নির্যাস",
        companyName = "Square Herbal & Nutraceuticals Ltd.",
        companyNameBn = "স্কয়ার হার্বাল অ্যান্ড নিউট্রাসিউটিক্যালস লি.",
        strength = "100 ml",
        dosageForm = "Herbal Syrup",
        dosageFormBn = "হার্বাল সিরাপ",
        category = MedicineCategory.HERBAL.storageKey,
        priceMrp = 85.00,
        packUnit = "100 ml bottle",
        packUnitBn = "১০০ মি.লি. বোতল",
        popularityScore = 93,
        indicationsEn =
          "Dry and productive cough, common cold, sore throat, bronchial congestion, and smoker's cough without causing drowsiness.",
        indicationsBn =
          "শুকনো ও কফযুক্ত কাশি, সাধারণ সর্দি, গলা ব্যথা ও শ্বাসনালীর কফ পরিষ্কারে কার্যকর (ঘুম ভাব ছাড়াই)।",
        dosageEn =
          "Adults: 3 teaspoons (15 ml) 2–3 times daily. Children (<12 yrs): 1–2 teaspoons (5–10 ml) 2–3 times daily.",
        dosageBn =
          "প্রাপ্তবয়স্ক: ৩ চা চামচ (১৫ মি.লি.) দিনে ২–৩ বার। শিশু: ১–২ চা চামচ দিনে ২–৩ বার।",
        pharmacologyEn =
          "Vasicine from Adhatoda vasica acts as a bronchodilator and mucolytic, while Tulsi and Licorice soothe pharyngeal mucosa.",
        pharmacologyBn =
          "বাসকের ভাসিসিন কফ তরল করে বের করতে সাহায্য করে এবং তুলসী ও যষ্টিমধু গলার প্রদাহ প্রশমিত করে।",
        contraindicationsEn = "Known allergy to any herbal constituent.",
        contraindicationsBn = "যেকোনো ভেষজ উপাদানে অ্যালার্জি থাকলে সেবন করবেন না।",
        sideEffectsEn = "Generally well tolerated; rare mild gastric warmth.",
        sideEffectsBn = "সাধারণত নিরাপদ এবং কোনো উল্লেখযোগ্য পার্শ্বপ্রতিক্রিয়া নেই।",
        precautionsEn =
          "Diabetic patients should check sugar content or opt for sugar-free variant; shake bottle well before use.",
        precautionsBn =
          "ডায়াবেটিক রোগীদের চিনিমুক্ত ভ্যারিয়েন্ট বেছে নেওয়া উচিত; ব্যবহারের আগে বোতল ভালোভাবে ঝাঁকিয়ে নিন।",
      ),
      MedicineEntity(
        id = 22,
        brandName = "Safi",
        brandNameBn = "সাফি",
        genericName = "Senna + Neem + Chirata + Tulsi Herbal Extract",
        genericNameBn = "সোনাপাতা + নিম + চিরতা + তুলসী নির্যাস",
        companyName = "Hamdard Laboratories (Waqf) Bangladesh",
        companyNameBn = "হামদর্দ ল্যাবরেটরিজ (ওয়াকফ) বাংলাদেশ",
        strength = "200 ml",
        dosageForm = "Herbal Syrup",
        dosageFormBn = "হার্বাল সিরাপ",
        category = MedicineCategory.HERBAL.storageKey,
        priceMrp = 120.00,
        packUnit = "200 ml bottle",
        packUnitBn = "২০০ মি.লি. বোতল",
        popularityScore = 84,
        indicationsEn =
          "Traditional herbal blood purifier supporting skin clarity (acne, pimples, boils), mild constipation relief, and digestive wellness.",
        indicationsBn =
          "ত্বকের ব্রণ, ফুসকুড়ি, রক্ত পরিশোধন এবং হজম ও কোষ্ঠকাঠিন্য দূর করতে সহায়ক ভেষজ সিরাপ।",
        dosageEn =
          "Adults: 2 teaspoons (10 ml) mixed in a glass of fresh water once daily in the morning.",
        dosageBn =
          "প্রাপ্তবয়স্ক: প্রতিদিন সকালে ২ চা চামচ (১০ মি.লি.) এক গ্লাস পানির সাথে মিশিয়ে সেব্য।",
        pharmacologyEn =
          "Natural botanicals promote bowel regularity, hepatic detoxification support, and mild anti-inflammatory skin effects.",
        pharmacologyBn =
          "প্রাকৃতিক ভেষজ উপাদান পরিপাকতন্ত্র ও ত্বকের প্রদাহ কমাতে সহায়তা করে।",
        contraindicationsEn = "Pregnancy, lactation, intestinal obstruction, or acute diarrhea.",
        contraindicationsBn = "গর্ভাবস্থা, স্তন্যদানকালীন এবং তীব্র ডায়রিয়ায় সেবন নিষিদ্ধ।",
        sideEffectsEn = "Mild loose stools if dose exceeds recommendation.",
        sideEffectsBn = "অতিরিক্ত মাত্রায় সেবন করলে হালকা পাতলা পায়খানা হতে পারে।",
        precautionsEn = "Maintain adequate hydration while taking herbal laxative formulations.",
        precautionsBn = "সেবনের সময় পর্যাপ্ত পরিমাণে বিশুদ্ধ পানি পান করুন।",
      ),
      MedicineEntity(
        id = 23,
        brandName = "Cinkara",
        brandNameBn = "সিনকারা",
        genericName = "Polyherbal Vitamins & Mineral Tonic",
        genericNameBn = "পলিহার্বাল ভিটামিন ও মিনারেল টনিক",
        companyName = "Hamdard Laboratories (Waqf) Bangladesh",
        companyNameBn = "হামদর্দ ল্যাবরেটরিজ (ওয়াকফ) বাংলাদেশ",
        strength = "200 ml",
        dosageForm = "Herbal Tonic",
        dosageFormBn = "হার্বাল টনিক",
        category = MedicineCategory.HERBAL.storageKey,
        priceMrp = 135.00,
        packUnit = "200 ml bottle",
        packUnitBn = "২০০ মি.লি. বোতল",
        popularityScore = 81,
        indicationsEn =
          "General debility, loss of appetite, convalescence after illness, and nutritional fatigue.",
        indicationsBn =
          "শারীরিক দুর্বলতা, অরুচি, অসুস্থতা পরবর্তী দুর্বলতা এবং পুষ্টিহীনতা দূর করতে সহায়ক।",
        dosageEn =
          "Adults: 2–3 teaspoons twice daily after meals. Children: 1 teaspoon twice daily after meals.",
        dosageBn =
          "প্রাপ্তবয়স্ক: খাবারের পর ২–৩ চা চামচ দিনে ২ বার। শিশু: ১ চা চামচ দিনে ২ বার।",
        pharmacologyEn =
          "Combines herbal extracts (Cardamom, Clove, Coriander, Cinnamon) with essential micronutrients to stimulate appetite and energy metabolism.",
        pharmacologyBn =
          "এলাচ, লবঙ্গ, ধনিয়া ও অন্যান্য ভেষজের সাথে ভিটামিন সমৃদ্ধ যা রুচি ও শক্তি বৃদ্ধি করে।",
        contraindicationsEn = "Hypersensitivity to any ingredient.",
        contraindicationsBn = "যেকোনো উপাদানে অ্যালার্জি থাকলে ব্যবহার করবেন না।",
        sideEffectsEn = "Minimal; generally well tolerated.",
        sideEffectsBn = "সাধারণত কোনো উল্লেখযোগ্য পার্শ্বপ্রতিক্রিয়া নেই।",
        precautionsEn = "Use with caution in diabetes mellitus due to syrup base.",
        precautionsBn = "ডায়াবেটিস রোগীরা চিকিৎসকের পরামর্শ নিয়ে সেবন করুন।",
      ),
      // --- Veterinary Medicines (Bangladesh Livestock & Poultry) ---
      MedicineEntity(
        id = 24,
        brandName = "Renamycin Vet",
        brandNameBn = "রেনামাইসিন ভেট",
        genericName = "Oxytetracycline Hydrochloride",
        genericNameBn = "অক্সিটেট্রাসাইক্লিন হাইড্রোক্লোরাইড",
        companyName = "Renata PLC (Animal Health)",
        companyNameBn = "রেনাটা পিএলসি (এনিমেল হেলথ)",
        strength = "50 mg/ml",
        dosageForm = "Vet Injection",
        dosageFormBn = "ভেটেরিনারি ইনজেকশন",
        category = MedicineCategory.VETERINARY.storageKey,
        priceMrp = 185.00,
        packUnit = "100 ml vial",
        packUnitBn = "১০০ মি.লি. ভায়াল",
        popularityScore = 80,
        indicationsEn =
          "Broad-spectrum veterinary antibiotic for cattle, goats, and sheep: hemorrhagic septicemia, pneumonia, mastitis, metritis, and foot rot.",
        indicationsBn =
          "গবাদি পশু ও ছাগলের নিউমোনিয়া, ওলান পাকা (ম্যাস্টাইটিস), ক্ষুরারোগের সেকেন্ডারি সংক্রমণ এবং ব্যাকটেরিয়াল জ্বরের চিকিৎসায়।",
        dosageEn =
          "Cattle/Buffalo/Goat: 5–10 mg/kg body weight (1–2 ml per 10 kg) daily via deep IM or slow IV for 3–5 days as directed by a registered veterinarian.",
        dosageBn =
          "গরু/মহিষ/ছাগল: প্রতি ১০ কেজি দৈহিক ওজনে ১–২ মি.লি. গভীর মাংসপেশীতে ৩–৫ দিন (ভেটেরিনারি চিকিৎসকের পরামর্শে)।",
        pharmacologyEn =
          "Tetracycline-class bacteriostatic antibiotic inhibiting bacterial protein synthesis by binding to the 30S ribosomal subunit.",
        pharmacologyBn =
          "ব্রড-স্পেকট্রাম টেট্রাসাইক্লিন অ্যান্টিবায়োটিক যা ব্যাকটেরিয়ার 30S রাইবোসোমে যুক্ত হয়ে বংশবিস্তার রোধ করে।",
        contraindicationsEn =
          "Known tetracycline hypersensitivity or severe renal/hepatic failure in livestock.",
        contraindicationsBn =
          "টেট্রাসাইক্লিনে সংবেদনশীলতা বা গুরুতর কিডনি-লিভার সমস্যায় ব্যবহার নিষিদ্ধ।",
        sideEffectsEn = "Temporary injection-site swelling or transient reduced appetite.",
        sideEffectsBn = "ইনজেকশন দেওয়ার স্থানে সামান্য ফোলা ভাব হতে পারে।",
        precautionsEn =
          "Observe mandatory withdrawal period (21 days for meat, 7 days for milk) before human consumption.",
        precautionsBn =
          "চিকিৎসা শেষ হওয়ার পর মাংসের জন্য ২১ দিন এবং দুধের জন্য ৭ দিন প্রত্যাহারকাল (Withdrawal period) মেনে চলুন।",
      ),
      MedicineEntity(
        id = 25,
        brandName = "Vermic Bolus",
        brandNameBn = "ভার্মিক বোলাস",
        genericName = "Albendazole + Levamisole",
        genericNameBn = "অ্যালবেন্ডাজল + লেভামিসল",
        companyName = "Techno Drugs Ltd.",
        companyNameBn = "টেকনো ড্রাগস লি.",
        strength = "600 mg + 300 mg",
        dosageForm = "Vet Bolus",
        dosageFormBn = "ভেটেরিনারি বোলাস",
        category = MedicineCategory.VETERINARY.storageKey,
        priceMrp = 18.00,
        packUnit = "per bolus",
        packUnitBn = "প্রতি বোলাস",
        popularityScore = 79,
        indicationsEn =
          "Broad-spectrum veterinary anthelmintic against gastrointestinal roundworms, lungworms, tapeworms, and adult liver flukes in cattle and goats.",
        indicationsBn =
          "গরু, মহিষ ও ছাগলের গোলকৃমি, ফিতাকৃমি ও কলিজা কৃমি (Liver fluke) দমনে ব্যবহৃত।",
        dosageEn =
          "1 bolus per 75–100 kg body weight orally as a single dose; repeat every 3–4 months per vet deworming schedule.",
        dosageBn =
          "প্রতি ৭৫–১০০ কেজি দৈহিক ওজনের জন্য ১টি বোলাস মুখে খাওয়াতে হবে।",
        pharmacologyEn =
          "Dual-action dewormer: albendazole blocks parasite glucose uptake while levamisole induces neuromuscular paralysis of helminths.",
        pharmacologyBn =
          "দ্বিমুখী কার্যকারিতার মাধ্যমে পরজীবী কৃমির শক্তি উৎপাদন বন্ধ ও পক্ষাঘাত ঘটিয়ে শরীর থেকে বের করে দেয়।",
        contraindicationsEn = "Avoid during first 45 days of pregnancy in cattle/ewes.",
        contraindicationsBn = "গর্ভবতী পশুর প্রথম ৪৫ দিনে ব্যবহার পরিহার করুন।",
        sideEffectsEn = "Rare transient salivation in sensitive animals.",
        sideEffectsBn = "কদাচিৎ সামান্য লালা ঝরতে পারে।",
        precautionsEn = "Follow milk and meat withdrawal intervals recommended by veterinarian.",
        precautionsBn = "ডোজ সঠিকভাবে ওজন অনুযায়ী নির্ধারণ করুন এবং ভেটেরিনারি চিকিৎসকের পরামর্শ নিন।",
      ),
      MedicineEntity(
        id = 26,
        brandName = "Oxyvet Bolus",
        brandNameBn = "অক্সিভেট বোলাস",
        genericName = "Oxytetracycline Hydrochloride",
        genericNameBn = "অক্সিটেট্রাসাইক্লিন হাইড্রোক্লোরাইড",
        companyName = "ACME Laboratories Ltd. (Vet)",
        companyNameBn = "একমি ল্যাবরেটরিজ লি. (ভেট)",
        strength = "500 mg",
        dosageForm = "Vet Bolus",
        dosageFormBn = "ভেটেরিনারি বোলাস",
        category = MedicineCategory.VETERINARY.storageKey,
        priceMrp = 22.00,
        packUnit = "per bolus",
        packUnitBn = "প্রতি বোলাস",
        popularityScore = 74,
        indicationsEn =
          "Bacterial scours (calf diarrhea), uterine infections (retained placenta), and enteric bacterial infections in ruminants.",
        indicationsBn =
          "বাছুর ও গবাদি পশুর ব্যাকটেরিয়াজনিত পাতলা পায়খানা এবং জরায়ু সংক্রমণের চিকিৎসায়।",
        dosageEn =
          "1 bolus per 50 kg body weight orally twice daily for 3–5 days, or intrauterine use per veterinarian.",
        dosageBn =
          "প্রতি ৫০ কেজি দৈহিক ওজনে ১টি বোলাস দিনে ২ বার ৩–৫ দিন (ভেটেরিনারি ডাক্তারের পরামর্শ অনুযায়ী)।",
        pharmacologyEn =
          "Oral and intrauterine broad-spectrum tetracycline active against Gram-positive and Gram-negative veterinary pathogens.",
        pharmacologyBn =
          "গ্রাম-পজিটিভ ও গ্রাম-নেগেটিভ ব্যাকটেরিয়ার বিরুদ্ধে কার্যকর ভেটেরিনারি অ্যান্টিবায়োটিক।",
        contraindicationsEn = "Hypersensitivity to tetracyclines; young calves with renal insufficiency.",
        contraindicationsBn = "টেট্রাসাইক্লিনে সংবেদনশীল পশুতে ব্যবহার নিষিদ্ধ।",
        sideEffectsEn = "Occasional digestive upset on prolonged oral administration.",
        sideEffectsBn = "দীর্ঘদিন খাওয়ালে পশুর হজমে সামান্য পরিবর্তন হতে পারে।",
        precautionsEn = "Observe milk/meat withdrawal period strictly.",
        precautionsBn = "চিকিৎসা চলাকালে দুধ ও মাংসের প্রত্যাহারকাল মেনে চলুন।",
      ),
    )
}
