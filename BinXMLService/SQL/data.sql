SELECT DISTINCT 
Cast(substr(binlow,0,7) as Integer) PrefixLower 
,Cast(substr(binhigh,0,7) as Integer) PrefixUpper 
,CASE 
  WHEN cardbrand='CI' THEN 15 
  ELSE PAN 
END 
CardLength 
,'Prepaid' CardSubClass 
,CASE cardbrand 
  WHEN 'DI' THEN 'Discover' 
  WHEN 'VI' THEN 'Visa' 
  WHEN 'MC' THEN 'MasterCard' 
  WHEN 'CI' THEN 'Convenience cards' 
  ELSE '-' 
END 
CardClassName 
,'FirstData' Provider 
,'XX' Country 
FROM pr019dlib.cardinfo WHERE PREPAID ='Y' 
and PAN > 13 and PAN < 19 and cardbrand <> '' 
order by PrefixLower
