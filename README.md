# tuntikirjanpito

git init (initialisoi tyhjä kansio = luo .git folderin johon tulee git setit)

git remote add origin https://urlijossarepoon

git pull

git clone https:urlijostaladataan 

git status (zekkaa gitin status ja muutokset)

git add . (addaa kaikki kansiossa olevat kamat gittiin)

git commit -m "kuvaus muutoksille" (committaa muutokset lokaaliin repoon)

git push origin master (origin=repon urli, master=branchin nimi -> voi olla myös esim branch2)

git checkout branch_name (vaihtaa branchia -> voi olla myös master)

git checkout -b branch_name  (luo uuden branchin)



Kun aloitat työskentelyn:
git pull

Kun olet tehnyt muutoksia:
git add file (filen nimi jota olet muokannut, jos olet muokannut montaa -> LISÄÄ NE KAIKKI)
git commit -m "commitin kuvaus"
git push origin master
